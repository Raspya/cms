package dev.bernouy.cms.tdb;

import dev.bernouy.cms.feature.website.component.Component;
import dev.bernouy.cms.feature.website.component.service.BusinessLogicComponentService;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.formatting.request.ReqCreateVersion;
import dev.bernouy.cms.feature.website.version.service.BusinessLogicVersionService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionTDB {

    private BusinessLogicVersionService versionService;
    private ComponentTDB componentTDB;

    private String    type;
    private Component component;

    @Autowired
    public VersionTDB(BusinessLogicVersionService versionService, ComponentTDB componentTDB){
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
        ReqCreateVersion reqCreateVersion = new ReqCreateVersion();
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
