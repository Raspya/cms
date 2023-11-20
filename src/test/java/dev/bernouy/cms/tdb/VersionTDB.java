package dev.bernouy.cms.tdb;

import dev.bernouy.cms.feature.website.component.Component;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.dto.req.ReqCreateVersionDTO;
import dev.bernouy.cms.feature.website.version.service.BusinessVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionTDB {

    private BusinessVersionService versionService;
    private ComponentTDB componentTDB;

    private String    type;
    private Component component;

    @Autowired
    public VersionTDB(BusinessVersionService versionService, ComponentTDB componentTDB){
        this.versionService = versionService;
        this.componentTDB = componentTDB;
    }

    public VersionTDB withType(String type){
        this.type = type;
        return this;
    }

    public VersionTDB withComponent(Component component){
        this.component = component;
        return this;
    }

    public Version build(){
        if (component == null) component = componentTDB.build();
        if (type == null) type = "MAJOR";
        ReqCreateVersionDTO reqCreateVersion = new ReqCreateVersionDTO();
        reqCreateVersion.setTypeVersion(type);
        reqCreateVersion.setComponentId(component.getId());
        Version v = versionService.create(reqCreateVersion, component.getProject().getOwner());
        reset();
        return v;
    }

    public void reset(){
        type = "MAJOR";
        component = null;
    }

}
