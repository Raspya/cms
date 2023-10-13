package dev.bernouy.cms.feature.website.layout;

import dev.bernouy.cms.common.AbstractDocument;
import dev.bernouy.cms.feature.website.project.Project;

public class Layout extends AbstractDocument {

    private String name;
    private boolean aBoolean;
    private Project project;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
