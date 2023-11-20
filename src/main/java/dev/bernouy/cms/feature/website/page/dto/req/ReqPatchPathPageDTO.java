package dev.bernouy.cms.feature.website.page.dto.req;

public class ReqPatchPathPageDTO {

    private String path;

    public ReqPatchPathPageDTO() {}
    public ReqPatchPathPageDTO(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
