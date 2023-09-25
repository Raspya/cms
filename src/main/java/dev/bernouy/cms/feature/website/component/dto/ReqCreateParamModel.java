package dev.bernouy.cms.feature.website.component.dto;

public class ReqCreateParamModel {

    private String versionId;
    private String type;


    public ReqCreateParamModel() {}
    public ReqCreateParamModel(String versionId, String type){
        this.versionId = versionId;
        this.type = type;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
