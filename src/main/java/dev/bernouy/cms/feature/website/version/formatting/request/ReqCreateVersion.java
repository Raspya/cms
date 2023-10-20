package dev.bernouy.cms.feature.website.version.formatting.request;

public class ReqCreateVersion {

    private String componentID;
    private String typeVersion;

    public ReqCreateVersion(){}
    public ReqCreateVersion(String typeVersion, String componentID) {
        this.typeVersion = typeVersion;
        this.componentID = componentID;
    }

    public String getComponentID() {
        return componentID;
    }

    public void setComponentID(String componentID) {
        this.componentID = componentID;
    }

    public String getTypeVersion() {
        return typeVersion;
    }

    public void setTypeVersion(String typeVersion) {
        this.typeVersion = typeVersion;
    }
}
