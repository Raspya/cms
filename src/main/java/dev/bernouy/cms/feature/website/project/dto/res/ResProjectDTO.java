package dev.bernouy.cms.feature.website.project.dto.res;

public class ResProjectDTO {

    private String id;
    private String name;
    private String domain;

    public ResProjectDTO(String id, String name, String domain){
        this.id = id;
        this.name = name;
        this.domain = domain;
    }

    public ResProjectDTO() {
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
