package dev.bernouy.cms.feature.website.page.dto;

public class ReqSetNamePage {

    private String name;

    public ReqSetNamePage() {}
    public ReqSetNamePage(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
