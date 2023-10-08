package dev.bernouy.cms.feature.website.page.dto;

public class ReqSetDescriptionPage {

    private String description;

    public ReqSetDescriptionPage() {}
    public ReqSetDescriptionPage(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
