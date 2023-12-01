package dev.bernouy.cms.feature.website.builder.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.AuthWebsiteService;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.builder.dto.req.ReqCreateBuilderDTO;
import dev.bernouy.cms.feature.website.builder.dto.req.ReqPositionBuilderDTO;
import dev.bernouy.cms.feature.website.builder.dto.res.ResBuilderDTO;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.layout.service.BusinessLayoutService;
import dev.bernouy.cms.feature.website.layout.service.PersistentLayoutService;
import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.page.service.BusinessPageService;
import dev.bernouy.cms.feature.website.page.service.PersistentPageService;
import dev.bernouy.cms.feature.website.paramBuilder.ParamBuilder;
import dev.bernouy.cms.feature.website.paramBuilder.dto.req.ReqCreateParamBuilderDTO;
import dev.bernouy.cms.feature.website.paramBuilder.service.BusinessParamBuilderService;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModelList;
import dev.bernouy.cms.feature.website.paramModel.service.PersistentParamModelService;
import dev.bernouy.cms.feature.website.project.service.AuthProjectService;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.service.BusinessVersionService;
import dev.bernouy.cms.feature.website.version.service.PersistentVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessBuilderService {

    private PersistentBuilderService dataPersistentBuilderService;
    private RegexComponent regexComponent;
    private BusinessVersionService versionService;
    private BusinessPageService businessLogicPageService;
    private BusinessLayoutService businessLogicLayoutService;
    private AuthWebsiteService authWebsiteService;
    private BusinessParamBuilderService businessLogicParamBuilderService;
    private FormattingBuilderService dataFormattingBuilderService;
    private PersistentParamModelService dataPersistentParamModelService;
    private PersistentVersionService persistentVersionService;
    private AuthProjectService authProjectService;
    private PersistentPageService persistentPageService;
    private PersistentLayoutService persistentLayoutService;

    @Autowired
    public BusinessBuilderService(PersistentLayoutService persistentLayoutService, PersistentPageService persistentPageService, AuthProjectService authProjectService, PersistentVersionService persistentVersionService, PersistentBuilderService dataPersistentBuilderService, RegexComponent regexComponent, BusinessVersionService versionService, BusinessPageService businessLogicPageService, BusinessLayoutService businessLogicLayoutService, AuthWebsiteService authWebsiteService, BusinessParamBuilderService businessLogicParamBuilderService, FormattingBuilderService dataFormattingBuilderService, PersistentParamModelService dataPersistentParamModelService) {
        this.dataPersistentBuilderService = dataPersistentBuilderService;
        this.regexComponent = regexComponent;
        this.versionService = versionService;
        this.businessLogicPageService = businessLogicPageService;
        this.businessLogicLayoutService = businessLogicLayoutService;
        this.authWebsiteService = authWebsiteService;
        this.businessLogicParamBuilderService = businessLogicParamBuilderService;
        this.dataFormattingBuilderService = dataFormattingBuilderService;
        this.dataPersistentParamModelService = dataPersistentParamModelService;
        this.persistentVersionService = persistentVersionService;
        this.authProjectService = authProjectService;
        this.persistentPageService = persistentPageService;
        this.persistentLayoutService = persistentLayoutService;
    }

    public Builder create(ReqCreateBuilderDTO dto, Account account) {
        Version version = persistentVersionService.getById(dto.getVersionId());
        authProjectService.checkIsOwner(version.getComponent().getProject(), account);
        Integer maxPos = null;
        Builder builder = new Builder();
        if ((dto.getLayoutId() == null && dto.getPageId() != null)){
            Page page = persistentPageService.getById(dto.getPageId());
            builder.setPage(page);
            maxPos = dataPersistentBuilderService.getMaxPosByPageId(page.getId());
        }
        else if ((dto.getLayoutId() != null && dto.getPageId() == null)){
            Layout layout = persistentLayoutService.getById(dto.getLayoutId());
            builder.setLayout(layout);
            maxPos = dataPersistentBuilderService.getMaxPosByLayoutId(layout.getId());
        } else {
            throw new BasicException(WebsiteExceptionMessages.INVALID_BUILDER_PAGEORLAYOUT);
        }
        builder.setComponentVersion(version);
        builder.setPosition(maxPos+1);
        dataPersistentBuilderService.save(builder);

        List<ParamModel> lstParamModel = dataPersistentParamModelService.findAllByVersion(version);
        for (ParamModel paramModel : lstParamModel) {
            ParamBuilder paramBuilder = businessLogicParamBuilderService.create(paramModel.getId(), builder.getId(), account);

            if (paramModel instanceof ParamModelList) {
                List<ParamModel> lstParamModelChild = dataPersistentParamModelService.findAllByParentId(paramModel.getId());
                int value = 1;
                if ( paramModel.getValue() != null ) value = Integer.parseInt(paramModel.getValue());
                for (int i = 0; i < value; i++) {
                    for (ParamModel paramModelChild : lstParamModelChild) {
                        businessLogicParamBuilderService.create(new ReqCreateParamBuilderDTO(paramBuilder.getId()), account);
                        if (lstParamModel.contains(paramModelChild)) lstParamModel.remove(paramModelChild); // TODO: 2023-11-19 check if it's necessary
                    }
                }
            }
        }
        return builder;
    }

    public void delete(String builderId, Account account) {
        Builder builder = dataPersistentBuilderService.getById(builderId);
        authProjectService.checkIsOwner(builder.getComponentVersion().getComponent().getProject(), account);
        dataPersistentBuilderService.delete(builder);
    }


    public void patchPosition(ReqPositionBuilderDTO dto, String builderId, Account account) {
        if (dto.getPosition() < 1) throw new BasicException(WebsiteExceptionMessages.INVALID_BUILDER_POSITION);
        Builder builder = dataPersistentBuilderService.getById(builderId);
        authProjectService.checkIsOwner(builder.getComponentVersion().getComponent().getProject(), account);
        Integer maxPos = dataPersistentBuilderService.getMaxPos(builder);
        if (dto.getPosition() > maxPos) throw new BasicException(WebsiteExceptionMessages.INVALID_BUILDER_POSITION);
        List<Builder> builderList = dataPersistentBuilderService.getBuilders(builder);
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

    public List<ResBuilderDTO> listAllBuilder(String layoutId, String pageId, Account account) {
        if (layoutId != null && pageId != null) throw new BasicException("LayoutId and PageId can't be together");
        if (layoutId == null && pageId == null) throw new BasicException("LayoutId or PageId is needed");
        if (layoutId != null) return dataFormattingBuilderService.formatBuilders(dataPersistentBuilderService.getBuildersByLayout(layoutId));
        return dataFormattingBuilderService.formatBuilders(dataPersistentBuilderService.getBuildersByPage(pageId));
    }
}
