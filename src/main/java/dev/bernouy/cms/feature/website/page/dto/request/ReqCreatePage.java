package dev.bernouy.cms.feature.website.page.dto.request;

public class ReqCreatePage {

    private String projectId;
    private String name;
    private String path;

    public ReqCreatePage() {}
    public ReqCreatePage(String projectId, String name, String path) {
        this.projectId = projectId;
        this.name = name;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
