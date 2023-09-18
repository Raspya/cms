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
    private int majorVersion = 1;
    private int minorVersion = 0;
    private int patchVersion = 0;
    private ComponentTDB componentTDB;
    private boolean isDeploy = false;

    private ReqTDB reqTDB;

    @Autowired
    public VersionBuilderTDB(ComponentBuilderTDB componentBuilderTDB, ReqTDB reqTDB){
        this.componentBuilderTDB = componentBuilderTDB;
        this.reqTDB = reqTDB;

    }

    public VersionBuilderTDB withMajorVersion(int majorVersion){
        this.majorVersion = majorVersion;
        return this;
    }

    public VersionBuilderTDB withMinorVersion(int minorVersion){
        this.minorVersion = minorVersion;
        return this;
    }

    public VersionBuilderTDB withPatchVersion(int patchVersion){
        this.patchVersion = patchVersion;
        return this;
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
        ReqCreateVersion dto = new ReqCreateVersion(this.majorVersion, this.minorVersion, this.patchVersion, this.componentTDB.getId());
        ResponseEntity<String> res = this.reqTDB.withAuth(this.componentTDB.getWebsite().getAccount().getCookie()).withDto(dto).send("/api/v1/component/version/create");
        VersionTDB versionTDB = new VersionTDB();
        versionTDB.setId(res.getBody());
        versionTDB.setMajorVersion(this.majorVersion);
        versionTDB.setMinorVersion(this.minorVersion);
        versionTDB.setPatchVersion(this.patchVersion);
        versionTDB.setComponentTDB(this.componentTDB);
        versionTDB.setDeploy(this.isDeploy);

        reset();
        return versionTDB;
    }

    public void reset(){
        this.majorVersion = 1;
        this.minorVersion = 0;
        this.patchVersion = 0;
        this.componentTDB = componentBuilderTDB.build();
        this.isDeploy = false;
    }



}
