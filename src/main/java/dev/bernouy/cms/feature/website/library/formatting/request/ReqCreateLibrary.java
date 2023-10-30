package dev.bernouy.cms.feature.website.library.formatting.request;

public class ReqCreateLibrary {

    private String projectId;
    private String name;

    public ReqCreateLibrary() {}
    public ReqCreateLibrary(String projectId, String name) {
        this.projectId = projectId;
        this.name = name;
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
}
