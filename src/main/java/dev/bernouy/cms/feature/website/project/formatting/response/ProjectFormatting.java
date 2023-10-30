package dev.bernouy.cms.feature.website.project.formatting.response;

public class ProjectFormatting {

    private String id;
    private String name;
    private String domain;

    public ProjectFormatting(String id, String name, String domain){
        this.id = id;
        this.name = name;
        this.domain = domain;
    }

    public ProjectFormatting() {
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
