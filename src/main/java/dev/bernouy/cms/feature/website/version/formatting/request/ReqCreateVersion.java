package dev.bernouy.cms.feature.website.version.formatting.request;

public class ReqCreateVersion {

    private String componentId;
    private String typeVersion;

    public ReqCreateVersion(){}
    public ReqCreateVersion(String typeVersion, String componentId) {
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
