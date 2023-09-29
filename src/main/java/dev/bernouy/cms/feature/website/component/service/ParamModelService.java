package dev.bernouy.cms.feature.website.component.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.component.ComponentExceptionMessages;
import dev.bernouy.cms.feature.website.component.dto.*;
import dev.bernouy.cms.feature.website.component.model.Version;
import dev.bernouy.cms.feature.website.component.model.paramModel.ParamModel;
import dev.bernouy.cms.feature.website.component.model.paramModel.ParamModelFloat;
import dev.bernouy.cms.feature.website.component.model.paramModel.ParamModelInt;
import dev.bernouy.cms.feature.website.component.model.paramModel.ParamModelString;
import dev.bernouy.cms.feature.website.component.repository.ParamModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.Param;

@Service
public class ParamModelService {

    private ParamModelRepository paramModelRepository;
    private RegexComponent regexComponent;
    private VersionService versionService;

    @Autowired
    public ParamModelService(ParamModelRepository paramModelRepository, RegexComponent regexComponent, VersionService versionService){
        this.regexComponent = regexComponent;
        this.paramModelRepository = paramModelRepository;
        this.versionService = versionService;
    }

    public ParamModel create(ReqCreateParamModel dto, Account account){

        Version componentVersion = versionService.getById(dto.getVersionId());
        versionService.authorizeAccount(componentVersion.getComponent(), account);

        ParamModel parent = null;
        if ( dto.getParentId() != null ){
            parent = paramModelRepository.findById(dto.getParentId()).orElse(null);
            if ( parent != null && !parent.childAvailable())
                throw new BasicException(ComponentExceptionMessages.INVALID_PARENT_TYPE);
        }

        String type = dto.getType().toLowerCase();
        ParamModel paramModel = switch (type) {
            case "string" -> new ParamModelString();
            case "int"    -> new ParamModelInt();
            case "float"  -> new ParamModelFloat();
            default       -> throw new BasicException(ComponentExceptionMessages.INVALID_PARAM_MODEL_TYPE);
        };
        paramModel.setType(dto.getType());
        paramModel.setComponentVersion(componentVersion);
        ParamModel paramModelMaxPos = paramModelRepository.findFirstByComponentVersionIdOrderByPositionDesc(componentVersion.getId());
        int maxPos;

        if (paramModelMaxPos == null) maxPos = 0;
        else maxPos = paramModelMaxPos.getPosition();

        paramModel.setPosition(maxPos+1);
        if ( parent != null ) paramModel.setParent(parent);
        paramModelRepository.save(paramModel);
        return paramModel;
    }

    public void delete(Account account, String paramModelId) {
        ParamModel paramModel = getById(paramModelId, account);
        paramModelRepository.delete(paramModel);
    }

    public void setKey(ReqKeyParamModel dto, Account account, String paramModelId) {
        regexComponent.isKeyValid(dto.getKey());
        ParamModel paramModel = getById(paramModelId, account);
        paramModel.setKey(dto.getKey());
        paramModelRepository.save(paramModel);
    }

    public void setName(ReqNameParamModel dto, Account account, String paramModelId) {
        ParamModel paramModel = getById(paramModelId, account);
        paramModel.setName(dto.getName());
    }

    public void setPosition(ReqPositionParamModel dto, Account account, String paramModelId) {
        ParamModel paramModel = getById(paramModelId, account);
        int position = paramModel.getPosition();
        int newPosition = dto.getPosition();

        if (newPosition <= 0 ) throw new BasicException(ComponentExceptionMessages.INVALID_PARAM_MODEL_POSITION);

        ParamModel paramModelTemp;
        if (newPosition < position ){
            for (int i=newPosition ; i<position ; i++){
                paramModelTemp = paramModelRepository.findByPosition(i);
                paramModelTemp.setPosition(i+1);
            }
        }

        else if (newPosition > position){
            for (int i=newPosition ; i>position ; i--){
                paramModelTemp = paramModelRepository.findByPosition(i);
                paramModelTemp.setPosition(i-1);
            }
        }

        paramModel.setPosition(newPosition);
    }

    public void setOption(ReqOptionParamModel dto, Account account, String paramModelId) {
        ParamModel paramModel = getById(paramModelId, account);
        paramModel.updateOption(dto.getKey(), dto.getValue());
    }

    public void resetOption(ReqKeyParamModel dto, Account account, String paramModelId) {
        ParamModel paramModel = getById(paramModelId, account);
        paramModel.updateOption(dto.getKey(), null);
    }

    public void resetOptions( Account account, String paramModelId) {
        ParamModel paramModel = getById(paramModelId, account);
        paramModel.resetOptions();
    }

    public ParamModel getById(String paramModelId, Account account) {
        authorizeAccount(paramModelId, account);
        ParamModel paramModel = paramModelRepository.findById(paramModelId).orElse(null);
        if (paramModel == null) throw new BasicException(ComponentExceptionMessages.INVALID_PARAM_MODEL_ID);
        return paramModel;
    }
    private void authorizeAccount(String paramModelId, Account account) {
        ParamModel paramModel = paramModelRepository.findById(paramModelId).orElse(null);
        if (paramModel == null || !paramModel.getComponentVersion().getComponent().getProject().getOwner().equals(account) )
            throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
    }
}
