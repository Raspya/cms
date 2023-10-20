package dev.bernouy.cms.feature.website.library;

import dev.bernouy.cms.common.AbstractDocument;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.version.Version;

import java.util.ArrayList;
import java.util.Set;

public class Library extends AbstractDocument {

    private String name;
    private ArrayList<Version> lstVersion;
    private Project project;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Version> getLstVersion() {
        return lstVersion;
    }

    public void setLstVersion(ArrayList<Version> lstVersion) {
        this.lstVersion = lstVersion;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
