package dev.bernouy.cms.feature.website.paramBuilder.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.builder.service.PersistentBuilderService;
import dev.bernouy.cms.feature.website.paramBuilder.ParamBuilder;
import dev.bernouy.cms.feature.website.paramBuilder.dto.req.ReqCreateParamBuilderDTO;
import dev.bernouy.cms.feature.website.paramBuilder.dto.req.ReqPatchValueParamBuilderDTO;
import dev.bernouy.cms.feature.website.paramBuilder.dto.res.ResParamBuilderDTO;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.paramModel.service.PersistentParamModelService;
import dev.bernouy.cms.feature.website.project.service.AuthProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessParamBuilderService {

    private PersistentBuilderService dataPersistentBuilderService;
    private PersistentParamBuilderService dataPersistentParamBuilderService;
    private PersistentParamModelService dataPersistentParamModelService;
    private AuthProjectService authProjectService;

    private FormattingParamBuilderService dataFormattingParamBuilderService;

    @Autowired
    public BusinessParamBuilderService(PersistentParamModelService dataPersistentParamModelService, FormattingParamBuilderService dataFormattingParamBuilderService, PersistentParamBuilderService dataPersistentParamBuilderService, PersistentBuilderService dataPersistentBuilderService, AuthProjectService authProjectService) {
        this.dataPersistentParamBuilderService = dataPersistentParamBuilderService;
        this.dataPersistentBuilderService = dataPersistentBuilderService;
        this.dataFormattingParamBuilderService = dataFormattingParamBuilderService;
        this.authProjectService = authProjectService;
        this.dataPersistentParamModelService = dataPersistentParamModelService;
    }

    public ParamBuilder create(ReqCreateParamBuilderDTO dto, Account account) {
        ParamBuilder paramBuilderParent = dataPersistentParamBuilderService.getById(dto.getParamBuilderId());
        Builder builder = dataPersistentBuilderService.getById(paramBuilderParent.getBuilder().getId());
        authProjectService.checkIsOwner(builder.getComponentVersion().getComponent().getProject(), account);
        ParamModel paramModel = dataPersistentParamModelService.getByParent(paramBuilderParent.getParamModel());

        ParamBuilder paramBuilder = new ParamBuilder();
        paramBuilder.setBuilder(builder);
        paramBuilder.setParamModel(paramModel);
        paramBuilder.setValue(paramModel.getValue());
        paramBuilder.setParent(paramModel.getId());
        dataPersistentParamBuilderService.save(paramBuilder);
        return paramBuilder;
    }

    public ParamBuilder create(String paramModelId, String builderId, Account account) {
        Builder builder = dataPersistentBuilderService.getById(builderId);
        authProjectService.checkIsOwner(builder.getComponentVersion().getComponent().getProject(), account);
        ParamModel paramModel = dataPersistentParamModelService.getById(paramModelId);

        ParamBuilder paramBuilder = new ParamBuilder();
        paramBuilder.setBuilder(builder);
        paramBuilder.setParamModel(paramModel);
        paramBuilder.setValue(paramModel.getValue());
        paramBuilder.setParent(paramModel.getParent());
        dataPersistentParamBuilderService.save(paramBuilder);
        return paramBuilder;
    }

    public void patchValue(String paramBuilderId, ReqPatchValueParamBuilderDTO dto, Account account) {
        ParamBuilder paramBuilder = dataPersistentParamBuilderService.getById(paramBuilderId);
        authProjectService.checkIsOwner(paramBuilder.getBuilder().getComponentVersion().getComponent().getProject(), account);
        ParamModel paramModel = dataPersistentParamModelService.getById(paramBuilder.getParamModel().getId());
        if(!paramModel.check(dto.getValue())) throw new BasicException(WebsiteExceptionMessages.INVALID_VALUE);
        paramBuilder.setValue(dto.getValue());
        dataPersistentParamBuilderService.save(paramBuilder);
    }

    public List<ResParamBuilderDTO> listAllParamBuilder(String builderId, String paramBuilderId, Account account) {
        if (paramBuilderId != null && builderId != null) throw new BasicException("builderId and paramBuilderId can't be together");
        if (paramBuilderId == null && builderId == null) throw new BasicException("builderId or paramBuilderId is needed");
        Builder builder = dataPersistentBuilderService.getById(builderId);
        builder.getComponentVersion();
        if (builderId != null)
            return dataFormattingParamBuilderService.formatParamBuilders(dataPersistentParamBuilderService.listAllParamBuilderById(builderId));
        ParamBuilder paramBuilder = dataPersistentParamBuilderService.getById(paramBuilderId);
        authProjectService.checkIsOwner(paramBuilder.getBuilder().getComponentVersion().getComponent().getProject(), account);
        ParamModel paramModel = dataPersistentParamModelService.getById(paramBuilder.getParamModel().getId());
        return dataFormattingParamBuilderService.formatParamBuilders(dataPersistentParamBuilderService.listAllParamBuilderByParamModelId(paramModel.getId()));

    }

}
