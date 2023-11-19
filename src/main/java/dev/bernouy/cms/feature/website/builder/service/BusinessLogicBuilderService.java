package dev.bernouy.cms.feature.website.builder.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.AuthWebsiteService;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.builder.formatting.request.ReqCreateBuilder;
import dev.bernouy.cms.feature.website.builder.formatting.request.ReqPositionBuilder;
import dev.bernouy.cms.feature.website.builder.formatting.response.BuilderFormatting;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.layout.service.BusinessLogicLayoutService;
import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.page.service.BusinessLogicPageService;
import dev.bernouy.cms.feature.website.paramBuilder.dto.request.ReqCreateParamBuilder;
import dev.bernouy.cms.feature.website.paramBuilder.service.BusinessLogicParamBuilderService;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModelList;
import dev.bernouy.cms.feature.website.paramModel.service.DataPersistentParamModelService;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.service.BusinessLogicVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessLogicBuilderService {

    private DataPersistentBuilderService dataPersistentBuilderService;
    private RegexComponent regexComponent;
    private BusinessLogicVersionService versionService;
    private BusinessLogicPageService businessLogicPageService;
    private BusinessLogicLayoutService businessLogicLayoutService;
    private AuthWebsiteService authWebsiteService;
    private BusinessLogicParamBuilderService businessLogicParamBuilderService;
    private DataFormattingBuilderService dataFormattingBuilderService;
    private DataPersistentParamModelService dataPersistentParamModelService;

    @Autowired
    public BusinessLogicBuilderService(DataFormattingBuilderService dataFormattingBuilderService, DataPersistentBuilderService dataPersistentBuilderService, RegexComponent regexComponent, BusinessLogicVersionService versionService, BusinessLogicPageService businessLogicPageService, BusinessLogicLayoutService businessLogicLayoutService, AuthWebsiteService authWebsiteService, BusinessLogicParamBuilderService businessLogicParamBuilderService, DataPersistentParamModelService dataPersistentParamModelService) {
        this.dataPersistentBuilderService = dataPersistentBuilderService;
        this.regexComponent = regexComponent;
        this.versionService = versionService;
        this.businessLogicPageService = businessLogicPageService;
        this.businessLogicLayoutService = businessLogicLayoutService;
        this.authWebsiteService = authWebsiteService;
        this.businessLogicParamBuilderService = businessLogicParamBuilderService;
        this.dataFormattingBuilderService = dataFormattingBuilderService;
        this.dataPersistentParamModelService = dataPersistentParamModelService;
    }

    public Builder create(ReqCreateBuilder dto, Account account) {
        versionService.getByIdAccount(dto.getVersionId(), account);
        Integer maxPos = null;
        Builder builder = new Builder();
        if ((dto.getLayoutId() == null && dto.getPageId() != null)){
            Page page = businessLogicPageService.getById(dto.getPageId(), account);
            builder.setPage(page);
            maxPos = dataPersistentBuilderService.findFirstByBuilderByPageId(page.getId()).getPosition();
        }
        else if ((dto.getLayoutId() != null && dto.getPageId() == null)){
            Layout layout = businessLogicLayoutService.getById(dto.getLayoutId(), account);
            builder.setLayout(layout);
            maxPos = dataPersistentBuilderService.findFirstByBuilderByLayoutId(layout.getId()).getPosition();
        } else {
            throw new BasicException(WebsiteExceptionMessages.INVALID_BUILDER_PAGEORLAYOUT);
        }
        Version version = versionService.getById(dto.getVersionId());
        builder.setComponentVersion(version);

        if (maxPos == null) maxPos = 0;
        builder.setPosition(maxPos+1);
        dataPersistentBuilderService.save(builder);

        ArrayList<ParamModel> lstParamModel = versionService.getLstParamModel(version.getId());
        for (ParamModel paramModel : lstParamModel) {
            businessLogicParamBuilderService.create(paramModel.getId(), builder.getId(), account);

            if (paramModel instanceof ParamModelList) {
                List<ParamModel> lstParamModelChild = dataPersistentParamModelService.findAllByParentId(paramModel.getId());
                int value = Integer.parseInt(paramModel.getValue());
                for (int i = 0; i < value; i++) {
                    for (ParamModel paramModelChild : lstParamModelChild) {
                        businessLogicParamBuilderService.create(new ReqCreateParamBuilder(paramModel.getId()), account);
                        if (lstParamModel.contains(paramModelChild)) lstParamModel.remove(paramModelChild); // TODO: 2023-11-19 check if it's necessary
                    }
                }
            }
        }
        return builder;
    }

    public void delete(String builderId, Account account) {
        Builder builder = dataPersistentBuilderService.getById(builderId, account);
        dataPersistentBuilderService.delete(builder);
    }


    public void setPosition(ReqPositionBuilder dto, String builderId,Account account) {
        if (dto.getPosition() < 1) throw new BasicException(WebsiteExceptionMessages.INVALID_BUILDER_POSITION);
        Builder builder = dataPersistentBuilderService.getById(builderId, account);
        Integer maxPos = dataPersistentBuilderService.findFirstByComponentVersionOrderByPositionDesc(builder.getComponentVersion().getId()).getPosition();

        if (dto.getPosition() > maxPos) throw new BasicException(WebsiteExceptionMessages.INVALID_BUILDER_POSITION);
        List<Builder> builderList = dataPersistentBuilderService.findAllByComponentVersionIdOrderByPositionAsc(builder.getComponentVersion().getId());
        List<Builder> toUpdate = new ArrayList<>();
        int i, min, max;
        if ( builder.getPosition() > dto.getPosition() ) {
            i = 1;
            min = dto.getPosition();
            max = builder.getPosition();
        }
        else {
            i = -1;
            min = builder.getPosition();
            max = dto.getPosition();
        }
        for ( int j = min ; j < max ; j++ ){
            builderList.get(j-1).setPosition(builderList.get(j-1).getPosition()+i);
            toUpdate.add(builderList.get(j-1));
        }
        builder.setPosition(dto.getPosition());
        toUpdate.add(builder);

        dataPersistentBuilderService.saveAll(toUpdate);
    }

    public List<BuilderFormatting> listAllBuilder(String layoutId, String pageId, Account account) {
        if (layoutId != null && pageId != null) throw new BasicException("LayoutId and PageId can't be together");
        if (layoutId == null && pageId == null) throw new BasicException("LayoutId or PageId is needed");
        if (layoutId != null) return dataFormattingBuilderService.formatBuilders(dataPersistentBuilderService.listAllBuilderByLayoutId(layoutId));
        return dataFormattingBuilderService.formatBuilders(dataPersistentBuilderService.listAllBuilderByPageId(pageId));
    }
}
