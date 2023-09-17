package dev.bernouy.cms.feature.website.component.dto;

public class ReqCreateComponentDTO {

    private String name;
    private String websiteId;

    public ReqCreateComponentDTO() {
    }

    public ReqCreateComponentDTO(String name, String websiteId) {
        this.name = name;
        this.websiteId = websiteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(String websiteId) {
        this.websiteId = websiteId;
    }
}
