package dev.bernouy.cms.feature.website.paramModel.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.paramModel.ParamModelRepository;
import dev.bernouy.cms.feature.website.paramModel.dto.req.*;
import dev.bernouy.cms.feature.website.paramModel.dto.response.ResParamModelDTO;
import dev.bernouy.cms.feature.website.paramModel.model.*;
import dev.bernouy.cms.feature.website.project.service.AuthProjectService;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.service.BusinessVersionService;
import dev.bernouy.cms.feature.website.version.service.PersistentVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessParamModelService {

    private ParamModelRepository paramModelRepository;
    private RegexComponent regexComponent;
    private FormattingParamModelService dataFormattingParamModelService;
    private PersistentParamModelService persistentParamModelService;
    private PersistentVersionService persistentVersionService;
    private AuthProjectService authProjectService;

    @Autowired
    public BusinessParamModelService(AuthProjectService authProjectService, PersistentParamModelService persistentParamModelService, PersistentVersionService persistentVersionService, ParamModelRepository paramModelRepository, RegexComponent regexComponent, BusinessVersionService versionService, FormattingParamModelService dataFormattingParamModelService){
        this.regexComponent = regexComponent;
        this.paramModelRepository = paramModelRepository;
        this.dataFormattingParamModelService = dataFormattingParamModelService;
        this.persistentParamModelService = persistentParamModelService;
        this.persistentVersionService = persistentVersionService;
        this.authProjectService = authProjectService;
    }

    public ParamModel create(ReqCreateParamModelDTO dto, Account account){

        Version componentVersion = persistentVersionService.getById(dto.getVersionId());
        authProjectService.checkIsOwner(componentVersion.getComponent().getProject(), account);

        ParamModel parent = null;
        if ( dto.getParentId() != null ){
            parent = paramModelRepository.findById(dto.getParentId()).orElse(null);
            if ( parent != null && !parent.childAvailable())
                throw new BasicException(WebsiteExceptionMessages.INVALID_PARENT_TYPE);
        }

        String type = dto.getType().toLowerCase();
        ParamModel paramModel = switch (type) {
            case "string" -> new ParamModelString();
            case "int"    -> new ParamModelInt();
            case "float"  -> new ParamModelFloat();
            case "list"   -> new ParamModelList();
            case "link"   -> new ParamModelLink();
            case "object" -> new ParamModelObject();
            default       -> throw new BasicException(WebsiteExceptionMessages.INVALID_PARAM_MODEL_TYPE);
        };
        paramModel.setType(dto.getType());
        paramModel.setComponentVersion(componentVersion);
        int maxPos = persistentParamModelService.getLastPosition(componentVersion);

        paramModel.setPosition(maxPos+1);
        if ( parent != null ) paramModel.setParent(parent.getId());

        persistentParamModelService.save(paramModel);
        return paramModel;
    }

    public void delete(Account account, String paramModelId) {
        ParamModel paramModel = persistentParamModelService.getById(paramModelId);
        authProjectService.checkIsOwner(paramModel.getComponentVersion().getComponent().getProject(), account);
        persistentParamModelService.delete(paramModel);
    }

    public void patchKey(ReqKeyParamModelDTO dto, Account account, String paramModelId) {
        regexComponent.isKeyValid(dto.getKey());
        ParamModel paramModel = persistentParamModelService.getById(paramModelId);
        authProjectService.checkIsOwner(paramModel.getComponentVersion().getComponent().getProject(), account);
        paramModel.setKey(dto.getKey());
        persistentParamModelService.save(paramModel);
    }

    public void patchName(ReqNameParamModelDTO dto, Account account, String paramModelId) {
        regexComponent.isNameValid(dto.getName());
        ParamModel paramModel = persistentParamModelService.getById(paramModelId);
        authProjectService.checkIsOwner(paramModel.getComponentVersion().getComponent().getProject(), account);
        paramModel.setName(dto.getName());
        persistentParamModelService.save(paramModel);
    }

    public void patchPosition(ReqPositionParamModelDTO dto, Account account, String paramModelId){
        if (dto.getPosition() < 1) throw new BasicException(WebsiteExceptionMessages.INVALID_PARAM_MODEL_POSITION);
        ParamModel paramModel = persistentParamModelService.getById(paramModelId);
        authProjectService.checkIsOwner(paramModel.getComponentVersion().getComponent().getProject(), account);
        List<ParamModel> paramModelList = persistentParamModelService.findAllByVersion(paramModel.getComponentVersion());
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
        persistentParamModelService.saveAll(toUpdate);
    }


    public void patchOption(ReqOptionParamModelDTO dto, Account account, String paramModelId) {
        ParamModel paramModel = persistentParamModelService.getById(paramModelId);
        authProjectService.checkIsOwner(paramModel.getComponentVersion().getComponent().getProject(), account);
        paramModel.updateOption(dto.getKey(), dto.getValue());
        persistentParamModelService.save(paramModel);
    }

    public void resetOption(ReqKeyParamModelDTO dto, Account account, String paramModelId) {
        ParamModel paramModel = persistentParamModelService.getById(paramModelId);
        authProjectService.checkIsOwner(paramModel.getComponentVersion().getComponent().getProject(), account);
        paramModel.resetOption(dto.getKey());
        persistentParamModelService.save(paramModel);
    }

    public void resetOptions( Account account, String paramModelId) {
        ParamModel paramModel = persistentParamModelService.getById(paramModelId);
        authProjectService.checkIsOwner(paramModel.getComponentVersion().getComponent().getProject(), account);
        paramModel.resetOptions();
        persistentParamModelService.save(paramModel);
    }

    public void patchValue(ReqValueParamModelDTO dto, Account account, String paramModelId) {
        ParamModel paramModel = persistentParamModelService.getById(paramModelId);
        authProjectService.checkIsOwner(paramModel.getComponentVersion().getComponent().getProject(), account);
        paramModel.setValue(dto.getValue());
        persistentParamModelService.save(paramModel);
    }

    public List<ResParamModelDTO> list(Account account, String paramModelId, String versionId) {
        return null;
    }

    public ResParamModelDTO getById(String paramModelId, Account account) {
        ParamModel paramModel = persistentParamModelService.getById(paramModelId);
        authProjectService.checkIsOwner(paramModel.getComponentVersion().getComponent().getProject(), account);
        return dataFormattingParamModelService.formatParamModel(paramModel);
    }
}