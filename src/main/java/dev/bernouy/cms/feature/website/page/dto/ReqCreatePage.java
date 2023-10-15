package dev.bernouy.cms.feature.website.page.dto;

public class ReqCreatePage {

    private String projectId;
    private String name;
    private String url;

    public ReqCreatePage() {}
    public ReqCreatePage(String projectId, String name, String url) {
        this.projectId = projectId;
        this.name = name;
        this.url = url;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
