package dev.bernouy.cms.tdb;

import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.builder.formatting.request.ReqCreateBuilder;
import dev.bernouy.cms.feature.website.builder.service.BusinessLogicBuilderService;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.paramModel.formatting.request.ReqCreateParamModel;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.paramModel.service.ParamModelService;
import dev.bernouy.cms.feature.website.version.Version;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuilderTDB {

    private BusinessLogicBuilderService builderService;
    private VersionTDB versionTDB;
    private PageTDB pageTDB;

    private Layout layout;
    private Version version;
    private Page page;

    @Autowired
    public BuilderTDB(BusinessLogicBuilderService builderService, VersionTDB versionTDB, PageTDB pageTDB){
        this.builderService = builderService;
        this.versionTDB = versionTDB;
        this.pageTDB = pageTDB;
    }

    public Builder build(){
        ReqCreateBuilder reqCreateBuilder = new ReqCreateBuilder();
        reqCreateBuilder.setVersionId(version.getId());
        reqCreateBuilder.setLayoutId(layout.getId());
        reqCreateBuilder.setPageId(page.getId());
        Builder b;
        if ( reqCreateBuilder.getLayoutId() == null && reqCreateBuilder.getPageId() == null )
            reqCreateBuilder.setPageId(pageTDB.build().getId());

        if (reqCreateBuilder.getVersionId() == null)
            reqCreateBuilder.setVersionId(versionTDB.build().getId());

        if ( reqCreateBuilder.getLayoutId() != null ) b = builderService.create(reqCreateBuilder, layout.getProject().getOwner());
        else b = builderService.create(reqCreateBuilder, page.getProject().getOwner());
        reset();
        return b;
    }

    public void reset(){
        layout = null;
        version = null;
        page = null;
    }

}
