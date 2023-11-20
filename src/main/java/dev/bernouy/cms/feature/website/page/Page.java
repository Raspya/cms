package dev.bernouy.cms.feature.website.page;

import dev.bernouy.cms.common.AbstractDocument;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.project.Project;

public class Page extends AbstractDocument {

    private Project project;
    private String name;
    private String title;
    private String path;
    private String description;
    private boolean isPublished;

    private Layout layout;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }
}
