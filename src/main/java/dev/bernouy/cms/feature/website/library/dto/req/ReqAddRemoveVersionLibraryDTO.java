package dev.bernouy.cms.feature.website.library.dto.req;

public class ReqAddRemoveVersionLibraryDTO {
    private String versionId;

    public ReqAddRemoveVersionLibraryDTO() {}
    public ReqAddRemoveVersionLibraryDTO(String versionId) {
        this.versionId = versionId;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
}
