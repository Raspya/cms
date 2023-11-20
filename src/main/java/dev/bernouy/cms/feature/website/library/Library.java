package dev.bernouy.cms.feature.website.library;

import dev.bernouy.cms.common.AbstractDocument;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.version.Version;

import java.util.List;

public class Library extends AbstractDocument {

    private String name;
    private List<Version> lstVersion;
    private Project project;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Version> getLstVersion() {
        return lstVersion;
    }

    public void setLstVersion(List<Version> lstVersion) {
        this.lstVersion = lstVersion;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
