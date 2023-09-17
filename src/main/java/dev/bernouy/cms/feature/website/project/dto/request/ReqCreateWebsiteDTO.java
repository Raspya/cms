package dev.bernouy.cms.feature.website.project.dto.request;

public class ReqCreateWebsiteDTO {

    private String name;

    public ReqCreateWebsiteDTO(){}
    public ReqCreateWebsiteDTO(String name ){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
