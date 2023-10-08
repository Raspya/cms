package dev.bernouy.cms.feature.website.page.dto;

public class ReqSetUrlPage {

    private String url;

    public ReqSetUrlPage() {}
    public ReqSetUrlPage(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
