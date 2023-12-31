package dev.bernouy.cms.feature.website.paramModel.dto.req;

public class ReqCreateParamModelDTO {

    private String versionId;
    private String type;
    private String parentId;


    public ReqCreateParamModelDTO() {}
    public ReqCreateParamModelDTO(String versionId, String type) {
        this.versionId = versionId;
        this.type = type;
    }

    public ReqCreateParamModelDTO(String versionId, String type, String parentId) {
        this.versionId = versionId;
        this.type = type;
        this.parentId = parentId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
