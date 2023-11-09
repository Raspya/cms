package dev.bernouy.cms.feature.website.page.dto.request;

public class ReqSetUrlPage {

    private String path;

    public ReqSetUrlPage() {}
    public ReqSetUrlPage(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
