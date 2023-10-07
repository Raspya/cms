package dev.bernouy.cms.feature.website.library.dto;

public class ReqCreateLibrary {

    private String projectId;

    public ReqCreateLibrary() {}
    public ReqCreateLibrary(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
