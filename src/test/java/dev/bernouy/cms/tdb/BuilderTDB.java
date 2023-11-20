package dev.bernouy.cms.tdb;

import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.builder.dto.req.ReqCreateBuilderDTO;
import dev.bernouy.cms.feature.website.builder.service.BusinessBuilderService;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.version.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuilderTDB {

    private BusinessBuilderService builderService;
    private VersionTDB versionTDB;
    private PageTDB pageTDB;

    private Layout layout;
    private Version version;
    private Page page;

    @Autowired
    public BuilderTDB(BusinessBuilderService builderService, VersionTDB versionTDB, PageTDB pageTDB){
        this.builderService = builderService;
        this.versionTDB = versionTDB;
        this.pageTDB = pageTDB;
    }

    public Builder build(){
        ReqCreateBuilderDTO reqCreateBuilder = new ReqCreateBuilderDTO();
        if ( layout == null && page == null )
            page = pageTDB.build();
        if (version == null)
            version = versionTDB.build();
        if ( version != null )
            reqCreateBuilder.setVersionId(version.getId());
        if ( layout != null )
            reqCreateBuilder.setLayoutId(layout.getId());
        if ( page != null )
            reqCreateBuilder.setPageId(page.getId());
        Builder b;

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
