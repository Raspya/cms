package dev.bernouy.cms.feature.website.component.dto;

public class ReqOptionParamModel {

    private String key;
    private Object value;

    public ReqOptionParamModel(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
