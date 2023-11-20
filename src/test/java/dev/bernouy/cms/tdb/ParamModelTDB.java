package dev.bernouy.cms.tdb;

import dev.bernouy.cms.feature.website.paramModel.dto.req.ReqCreateParamModelDTO;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import dev.bernouy.cms.feature.website.paramModel.service.BusinessParamModelService;
import dev.bernouy.cms.feature.website.version.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParamModelTDB {

    private BusinessParamModelService paramModelService;
    private VersionTDB versionTDB;

    private Version version;
    private String type;
    private ParamModel parent;

    @Autowired
    public ParamModelTDB(BusinessParamModelService paramModelService, VersionTDB versionTDB){
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
        ReqCreateParamModelDTO reqCreateParamModel = new ReqCreateParamModelDTO();
        reqCreateParamModel.setVersionId(version.getId());
        reqCreateParamModel.setType(type);
        if (parent != null)
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
