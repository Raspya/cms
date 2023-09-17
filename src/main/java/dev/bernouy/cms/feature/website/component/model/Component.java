package dev.bernouy.cms.feature.website.component.model;

import dev.bernouy.cms.common.AbstractDocument;
import dev.bernouy.cms.feature.website.project.Project;
import org.springframework.data.mongodb.core.mapping.DBRef;


public class Component extends AbstractDocument {

    private String name;
    @DBRef
    private Project project;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
