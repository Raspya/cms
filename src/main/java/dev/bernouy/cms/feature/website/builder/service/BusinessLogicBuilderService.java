package dev.bernouy.cms.feature.website.builder.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.AuthWebsiteService;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.builder.dto.ReqCreateBuilder;
import dev.bernouy.cms.feature.website.builder.dto.ReqPositionBuilder;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.layout.LayoutService;
import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.page.PageService;
import dev.bernouy.cms.feature.website.paramBuilder.ParamBuilder;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
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
    private PageService pageService;
    private LayoutService layoutService;
    private AuthWebsiteService authWebsiteService;

    @Autowired
    public BusinessLogicBuilderService(DataPersistentBuilderService dataPersistentBuilderService, RegexComponent regexComponent, BusinessLogicVersionService versionService, PageService pageService, LayoutService layoutService, AuthWebsiteService authWebsiteService) {
        this.dataPersistentBuilderService = dataPersistentBuilderService;
        this.regexComponent = regexComponent;
        this.versionService = versionService;
        this.pageService = pageService;
        this.layoutService = layoutService;
        this.authWebsiteService = authWebsiteService;
    }

    public Builder create(ReqCreateBuilder dto, Account account) {
        versionService.getByIdAccount(dto.getVersionId(), account);
        if (!(dto.getLayoutId() == null && dto.getPageId() != null) && !(dto.getLayoutId() != null && dto.getPageId() == null))
            throw new BasicException(WebsiteExceptionMessages.INVALID_BUILDER_PAGEORLAYOUT);

        Version version = versionService.getById(dto.getVersionId());
        Page page = pageService.getById(dto.getPageId(), account);
        Layout layout = layoutService.getById(dto.getLayoutId(), account);

        Builder builder = new Builder();
        builder.setPage(page);
        builder.setLayout(layout);
        builder.setComponentVersion(version);

        Integer maxPos = dataPersistentBuilderService.findFirstByComponentVersionOrderByPositionDesc(version.getId()).getPosition();
        if (maxPos == null) maxPos = 0;
        builder.setPosition(maxPos+1);
        dataPersistentBuilderService.save(builder);

        ArrayList<ParamModel> lstParamModel = versionService.getLstParamModel(version.getId());
        for (ParamModel paramModel : lstParamModel) {
            new ParamBuilder();
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

}
