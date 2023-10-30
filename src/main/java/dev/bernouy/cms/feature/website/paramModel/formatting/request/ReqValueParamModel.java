package dev.bernouy.cms.feature.website.paramModel.formatting.request;

public class ReqValueParamModel {

    private String value;

    public ReqValueParamModel() {}
    public ReqValueParamModel(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public void setName(String value) {
        this.value = value;
    }

}