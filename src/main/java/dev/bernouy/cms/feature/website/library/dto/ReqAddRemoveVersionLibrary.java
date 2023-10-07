package dev.bernouy.cms.feature.website.library.dto;

public class ReqAddRemoveVersionLibrary {
    private String versionId;

    public ReqAddRemoveVersionLibrary() {}
    public ReqAddRemoveVersionLibrary(String versionId) {
        this.versionId = versionId;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
}
