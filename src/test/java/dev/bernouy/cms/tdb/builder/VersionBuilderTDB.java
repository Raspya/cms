package dev.bernouy.cms.tdb.builder;

import dev.bernouy.cms.ReqTDB;
import dev.bernouy.cms.feature.website.component.dto.ReqCreateVersion;
import dev.bernouy.cms.feature.website.component.model.Version;
import dev.bernouy.cms.feature.website.component.service.VersionService;
import dev.bernouy.cms.tdb.pojo.ComponentTDB;
import dev.bernouy.cms.tdb.pojo.VersionTDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class VersionBuilderTDB {

    private ComponentBuilderTDB componentBuilderTDB;

    private ComponentTDB componentTDB;
    private boolean isDeploy = false;

    private ReqTDB reqTDB;

    @Autowired
    public VersionBuilderTDB(ComponentBuilderTDB componentBuilderTDB, ReqTDB reqTDB){
        this.componentBuilderTDB = componentBuilderTDB;
        this.reqTDB = reqTDB;

    }

    public VersionBuilderTDB withComponentTDB(ComponentTDB componentTDB){
        this.componentTDB = componentTDB;
        return this;
    }

    public VersionBuilderTDB withDeploy(boolean deploy){
        this.isDeploy = deploy;
        return this;
    }

    public VersionTDB build(){
        if (componentTDB == null) this.componentTDB = componentBuilderTDB.build();
        ReqCreateVersion dto = new ReqCreateVersion("", this.componentTDB.getId());
        ResponseEntity<String> res = this.reqTDB.withAuth(this.componentTDB.getWebsite().getAccount().getCookie()).withDto(dto).send("/api/v1/component/version/create");
        VersionTDB versionTDB = new VersionTDB();
        versionTDB.setId(res.getBody());
        versionTDB.setComponentTDB(this.componentTDB);
        versionTDB.setDeploy(this.isDeploy);

        reset();
        return versionTDB;
    }

    public void reset(){
        this.componentTDB = null;
        this.isDeploy = false;
    }



}
