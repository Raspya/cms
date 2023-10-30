package dev.bernouy.cms.feature.website.project.formatting.request;

public class ReqCreateWebsiteDTO {

    private String name;
    private String domain;

    public ReqCreateWebsiteDTO(){}
    public ReqCreateWebsiteDTO(String name, String domain){
        this.name = name;
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
