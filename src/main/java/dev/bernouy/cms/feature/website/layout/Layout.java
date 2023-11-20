package dev.bernouy.cms.feature.website.layout;

import dev.bernouy.cms.common.AbstractDocument;
import dev.bernouy.cms.feature.website.project.Project;

public class Layout extends AbstractDocument {

    private String name;
    private boolean isDefault;
    private Project project;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean aDefault) {
        this.isDefault = aDefault;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
