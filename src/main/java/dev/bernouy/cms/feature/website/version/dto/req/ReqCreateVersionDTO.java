package dev.bernouy.cms.feature.website.version.dto.req;

public class ReqCreateVersionDTO {

    private String componentId;
    private String typeVersion;

    public ReqCreateVersionDTO(){}
    public ReqCreateVersionDTO(String typeVersion, String componentId) {
        this.typeVersion = typeVersion;
        this.componentId = componentId;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getTypeVersion() {
        return typeVersion;
    }

    public void setTypeVersion(String typeVersion) {
        this.typeVersion = typeVersion;
    }
}
