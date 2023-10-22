package dev.bernouy.cms.feature.website.paramBuilder.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.AuthWebsiteService;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.builder.service.DataPersistentBuilderService;
import dev.bernouy.cms.feature.website.paramBuilder.ParamBuilder;
import dev.bernouy.cms.feature.website.paramBuilder.ParamBuilderRepository;
import dev.bernouy.cms.feature.website.paramBuilder.dto.ReqCreateParamBuilder;
import dev.bernouy.cms.feature.website.paramBuilder.dto.ReqSetValueParamBuilder;
import dev.bernouy.cms.feature.website.paramModel.ParamModelService;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessLogicParamBuilderService {

    private RegexComponent regexComponent;
    private AuthWebsiteService authWebsiteService;
    private ParamModelService paramModelService;
    private DataPersistentBuilderService dataPersistentBuilderService;
    private DataPersistentParamBuilderService dataPersistentParamBuilderService;


    @Autowired
    public BusinessLogicParamBuilderService(DataPersistentParamBuilderService dataPersistentParamBuilderService, RegexComponent regexComponent, AuthWebsiteService authWebsiteService, ParamModelService paramModelService, DataPersistentBuilderService dataPersistentBuilderService) {
        this.dataPersistentParamBuilderService = dataPersistentParamBuilderService;
        this.regexComponent = regexComponent;
        this.authWebsiteService = authWebsiteService;
        this.paramModelService = paramModelService;
        this.dataPersistentBuilderService = dataPersistentBuilderService;
    }

    public ParamBuilder create(ReqCreateParamBuilder dto, Account account) {
        ParamBuilder paramBuilderParent = dataPersistentParamBuilderService.getById(dto.getParamBuilderId(), account);
        Builder builder = dataPersistentBuilderService.getById(paramBuilderParent.getComponentBuilder().getId(), account);
        ParamModel paramModel = paramModelService.getByParentId(paramBuilderParent.getParamModel().getId(), account);

        ParamBuilder paramBuilder = new ParamBuilder();
        paramBuilder.setComponentBuilder(builder);
        paramBuilder.setParamModel(paramModel);
        paramBuilder.setValue(paramModel.getValue());
        return paramBuilder;
    }
    public ParamBuilder create(String paramModelId, String builderId, Account account) {
        Builder builder = dataPersistentBuilderService.getById(builderId, account);
        ParamModel paramModel = paramModelService.getById(paramModelId, account);

        ParamBuilder paramBuilder = new ParamBuilder();
        paramBuilder.setComponentBuilder(builder);
        paramBuilder.setParamModel(paramModel);
        paramBuilder.setValue(paramModel.getValue());
        return paramBuilder;
    }

    public void setValue(String paramBuilderId, ReqSetValueParamBuilder dto, Account account) {
        ParamBuilder paramBuilder = dataPersistentParamBuilderService.getById(paramBuilderId, account);
        ParamModel paramModel = paramModelService.getById(paramBuilder.getParamModel().getId(), account);
        if(!paramModel.check(dto.getValue())) throw new BasicException(WebsiteExceptionMessages.INVALID_VALUE);
        paramBuilder.setValue(dto.getValue());
        dataPersistentParamBuilderService.save(paramBuilder);
    }
}
