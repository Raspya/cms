package dev.bernouy.cms.feature.website.component.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.component.dto.ReqCreateParamModel;
import dev.bernouy.cms.feature.website.component.dto.ReqInfoParamModel;
import dev.bernouy.cms.feature.website.component.dto.ReqOptionParamModel;
import dev.bernouy.cms.feature.website.component.model.Component;
import dev.bernouy.cms.feature.website.component.model.ParamModel;
import dev.bernouy.cms.feature.website.component.model.Version;
import dev.bernouy.cms.feature.website.component.repository.ParamModelRepository;
import dev.bernouy.cms.feature.website.component.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
        Version componentVersion = versionService.getById(dto.getVersionId()); // A MODIFIER
        ParamModel paramModel = new ParamModel();
        paramModel.setType(dto.getType());
        paramModel.setComponentVersion(componentVersion);
        return paramModel;
    }

    public void delete(Account account, String paramModelId) {
        authorizeAccount(paramModelId, account);
        paramModelRepository.deleteById(paramModelId);
    }

    public void setKey(ReqInfoParamModel dto, Account account, String paramModelId) {
        authorizeAccount(paramModelId, account);
        ParamModel paramModel = paramModelRepository.findById(paramModelId).orElseThrow();
        paramModel.setKey(dto.getInfo());
    }
    public void setName(ReqInfoParamModel dto, Account account, String paramModelId) {
        authorizeAccount(paramModelId, account);
        ParamModel paramModel = paramModelRepository.findById(paramModelId).orElseThrow();
        paramModel.setName(dto.getInfo());
    }

    public void setPosition(Account account, String paramModelId) {
        authorizeAccount(paramModelId, account);
        ParamModel paramModel = paramModelRepository.findById(paramModelId).orElseThrow();
    }

    public void setOption(ReqOptionParamModel dto, Account account, String paramModelId) {
        authorizeAccount(paramModelId, account);
        ParamModel paramModel = paramModelRepository.findById(paramModelId).orElseThrow();
        paramModel.addOption(dto.getKey(), dto.getValue());
    }

    public void resetOption(ReqInfoParamModel dto, Account account, String paramModelId) {
        authorizeAccount(paramModelId, account);
        ParamModel paramModel = paramModelRepository.findById(paramModelId).orElseThrow();
        paramModel.removeOption(dto.getInfo());
    }

    public void resetOptions( Account account, String paramModelId) {
        authorizeAccount(paramModelId, account);
        ParamModel paramModel = paramModelRepository.findById(paramModelId).orElseThrow();
        paramModel.setOptions(new HashMap<String, Object>());
    }

    private void authorizeAccount(String paramModelId, Account account) {
        ParamModel paramModel = paramModelRepository.findById(paramModelId).orElseThrow();
        if (!paramModel.getComponentVersion().getComponent().getProject().getOwner().equals(account))
            throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
    }
}
