package dev.bernouy.cms.feature.website.paramModel.dto;

public class ReqNameParamModel {

    private String name;

    public ReqNameParamModel() {}
    public ReqNameParamModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}