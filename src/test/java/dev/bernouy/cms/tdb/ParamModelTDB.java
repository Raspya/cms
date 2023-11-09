package dev.bernouy.cms.tdb;

import dev.bernouy.cms.feature.website.library.Library;
import dev.bernouy.cms.feature.website.library.formatting.request.ReqCreateLibrary;
import dev.bernouy.cms.feature.website.library.service.BusinessLogicLibraryService;
import dev.bernouy.cms.feature.website.paramModel.formatting.request.ReqCreateParamModel;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.paramModel.service.ParamModelService;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.version.Version;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParamModelTDB {

    private ParamModelService paramModelService;
    private VersionTDB versionTDB;

    private Version version;
    private String type;
    private ParamModel parent;

    @Autowired
    public ParamModelTDB(ParamModelService paramModelService, VersionTDB versionTDB){
        this.paramModelService = paramModelService;
        this.versionTDB = versionTDB;
    }

    public ParamModelTDB withVersion(Version version){
        this.version = version;
        return this;
    }

    public ParamModelTDB withType(String type){
        this.type = type;
        return this;
    }

    public ParamModelTDB withParent(ParamModel parent){
        this.parent = parent;
        return this;
    }
    
    public ParamModel build(){
        if (version == null) version = versionTDB.build();
        if (type == null) type = "STRING";
        ReqCreateParamModel reqCreateParamModel = new ReqCreateParamModel();
        reqCreateParamModel.setVersionId(version.getId());
        reqCreateParamModel.setType(type);
        reqCreateParamModel.setParentId(parent.getId());
        ParamModel p = paramModelService.create(reqCreateParamModel, version.getComponent().getProject().getOwner());
        reset();
        return p;
    }

    public void reset(){
        version = null;
        type = "STRING";
        parent = null;
    }

}
