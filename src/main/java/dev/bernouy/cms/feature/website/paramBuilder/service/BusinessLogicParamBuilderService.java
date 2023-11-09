package dev.bernouy.cms.feature.website.paramBuilder.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.AuthWebsiteService;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.builder.service.DataPersistentBuilderService;
import dev.bernouy.cms.feature.website.paramBuilder.ParamBuilder;
import dev.bernouy.cms.feature.website.paramBuilder.dto.request.ReqCreateParamBuilder;
import dev.bernouy.cms.feature.website.paramBuilder.dto.request.ReqSetValueParamBuilder;
import dev.bernouy.cms.feature.website.paramBuilder.dto.response.ParamBuilderFormatting;
import dev.bernouy.cms.feature.website.paramModel.service.ParamModelService;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessLogicParamBuilderService {

    private RegexComponent regexComponent;
    private AuthWebsiteService authWebsiteService;
    private ParamModelService paramModelService;
    private DataPersistentBuilderService dataPersistentBuilderService;
    private DataPersistentParamBuilderService dataPersistentParamBuilderService;

    private DataFormattingParamBuilderService dataFormattingParamBuilderService;

    @Autowired
    public BusinessLogicParamBuilderService(DataFormattingParamBuilderService dataFormattingParamBuilderService,DataPersistentParamBuilderService dataPersistentParamBuilderService, RegexComponent regexComponent, AuthWebsiteService authWebsiteService, ParamModelService paramModelService, DataPersistentBuilderService dataPersistentBuilderService) {
        this.dataPersistentParamBuilderService = dataPersistentParamBuilderService;
        this.regexComponent = regexComponent;
        this.authWebsiteService = authWebsiteService;
        this.paramModelService = paramModelService;
        this.dataPersistentBuilderService = dataPersistentBuilderService;
        this.dataFormattingParamBuilderService = dataFormattingParamBuilderService;
    }

    public ParamBuilder create(ReqCreateParamBuilder dto, Account account) {
        ParamBuilder paramBuilderParent = dataPersistentParamBuilderService.getById(dto.getParamBuilderId(), account);
        Builder builder = dataPersistentBuilderService.getById(paramBuilderParent.getBuilder().getId(), account);
        ParamModel paramModel = paramModelService.getByParentId(paramBuilderParent.getParamModel().getId(), account);

        ParamBuilder paramBuilder = new ParamBuilder();
        paramBuilder.setBuilder(builder);
        paramBuilder.setParamModel(paramModel);
        paramBuilder.setValue(paramModel.getValue());
        return paramBuilder;
    }
    public ParamBuilder create(String paramModelId, String builderId, Account account) {
        Builder builder = dataPersistentBuilderService.getById(builderId, account);
        ParamModel paramModel = paramModelService.getById(paramModelId, account);

        ParamBuilder paramBuilder = new ParamBuilder();
        paramBuilder.setBuilder(builder);
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

    public List<ParamBuilderFormatting> listAllParamBuilder(String builderId, String paramBuilderId, Account account) {
        if (paramBuilderId != null && builderId != null) throw new BasicException("builderId and paramBuilderId can't be together");
        if (paramBuilderId == null && builderId == null) throw new BasicException("builderId or paramBuilderId is needed");
        if (builderId != null)
            return dataFormattingParamBuilderService.formatParamBuilders(dataPersistentParamBuilderService.listAllParamBuilderById(builderId));
        ParamBuilder paramBuilder = dataPersistentParamBuilderService.getById(paramBuilderId, account);
        ParamModel paramModel = paramModelService.getById(paramBuilder.getParamModel().getId(), account);
        return dataFormattingParamBuilderService.formatParamBuilders(dataPersistentParamBuilderService.listAllParamBuilderByParamModelId(paramModel.getId()));

    }

}
