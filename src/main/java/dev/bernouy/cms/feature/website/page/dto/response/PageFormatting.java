package dev.bernouy.cms.feature.website.page.dto.response;

public class PageFormatting {

    private String id;
    private String name;
    private String title;
    private String path;
    private String description;
    private Boolean isDeploy;
    private String layout;

    public PageFormatting(String id, String name, String title, String path, String description, Boolean isDeploy, String layout) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.path = path;
        this.description = description;
        this.isDeploy = isDeploy;
        this.layout = layout;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getDeploy() {
        return isDeploy;
    }

    public void setDeploy(Boolean deploy) {
        isDeploy = deploy;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }
}
