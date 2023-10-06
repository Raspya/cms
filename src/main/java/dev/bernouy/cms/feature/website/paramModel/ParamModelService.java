package dev.bernouy.cms.feature.website.paramModel;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.component.ComponentExceptionMessages;
import dev.bernouy.cms.feature.website.paramModel.dto.*;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModelFloat;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModelInt;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModelString;
import dev.bernouy.cms.feature.website.version.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.Param;

import java.util.ArrayList;
import java.util.List;

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
        regexComponent.isNameValid(dto.getName());
        ParamModel paramModel = getById(paramModelId, account);
        paramModel.setName(dto.getName());
        paramModelRepository.save(paramModel);
    }

    public void setPosition(ReqPositionParamModel dto, Account account, String paramModelId){
        ParamModel paramModel = getById(paramModelId, account);
        List<ParamModel> paramModelList = paramModelRepository.findAllByComponentVersionIdOrderByPositionAsc(paramModel.getComponentVersion().getId());
        List<ParamModel> toUpdate = new ArrayList<>();
        int i, min, max;
        if ( paramModel.getPosition() > dto.getPosition() ) {
            i = 1;
            min = dto.getPosition();
            max = paramModel.getPosition();
        }
        else {
            i = -1;
            min = paramModel.getPosition();
            max = dto.getPosition();
        }
        for ( int j = min ; j < max ; j++ ){
            paramModelList.get(j-1).setPosition(paramModelList.get(j-1).getPosition()+i);
            toUpdate.add(paramModelList.get(j-1));
        }
        paramModel.setPosition(dto.getPosition());
        toUpdate.add(paramModel);

        paramModelRepository.saveAll(toUpdate);
    }


    public void setOption(ReqOptionParamModel dto, Account account, String paramModelId) {
        ParamModel paramModel = getById(paramModelId, account);
        paramModel.updateOption(dto.getKey(), dto.getValue());
        paramModelRepository.save(paramModel);
    }

    public void resetOption(ReqKeyParamModel dto, Account account, String paramModelId) {
        ParamModel paramModel = getById(paramModelId, account);
        paramModel.resetOption(dto.getKey());
        paramModelRepository.save(paramModel);
    }

    public void resetOptions( Account account, String paramModelId) {
        ParamModel paramModel = getById(paramModelId, account);
        paramModel.resetOptions();
        paramModelRepository.save(paramModel);
    }

    public void setValue(ReqValueParamModel dto, Account account, String paramModelId) {
        ParamModel paramModel = getById(paramModelId, account);
        paramModel.setValue(dto.getValue());
        paramModelRepository.save(paramModel);

    }

    public ParamModel getById(String paramModelId, Account account) {
        ParamModel paramModel = paramModelRepository.findById(paramModelId).orElse(null);
        if (paramModel == null) throw new BasicException(ComponentExceptionMessages.INVALID_PARAM_MODEL_ID);
        authorizeAccount(paramModelId, account);
        return paramModel;
    }
    private void authorizeAccount(String paramModelId, Account account) {
        ParamModel paramModel = paramModelRepository.findById(paramModelId).orElse(null);
        if (paramModel == null || !paramModel.getComponentVersion().getComponent().getProject().getOwner().equals(account) )
            throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
    }

}
