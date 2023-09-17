package dev.bernouy.cms.feature.website.component.dto;

public class ReqCreateVersion {

    private String componentID;
    private int majorVersion;
    private int minorVersion;
    private int patchVersion;

    public ReqCreateVersion(){}
    public ReqCreateVersion(int majorVersion, int minorVersion, int patchVersion, String componentID) {
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.patchVersion = patchVersion;
        this.componentID = componentID;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getPatchVersion() {
        return patchVersion;
    }

    public void setPatchVersion(int patchVersion) {
        this.patchVersion = patchVersion;
    }

    public String getComponentID() {
        return componentID;
    }

    public void setComponentID(String componentID) {
        this.componentID = componentID;
    }
}
