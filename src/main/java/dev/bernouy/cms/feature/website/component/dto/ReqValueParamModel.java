package dev.bernouy.cms.feature.website.component.dto;

public class ReqValueParamModel {

    private Object value;

    public ReqValueParamModel() {}
    public ReqValueParamModel(Object value) {
        this.value = value;
    }
    public Object getValue() {
        return value;
    }

    public void setName(Object value) {
        this.value = value;
    }

}