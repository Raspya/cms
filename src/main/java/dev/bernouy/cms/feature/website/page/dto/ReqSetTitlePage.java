package dev.bernouy.cms.feature.website.page.dto;

public class ReqSetTitlePage {

    private String title;

    public ReqSetTitlePage() {}
    public ReqSetTitlePage(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
