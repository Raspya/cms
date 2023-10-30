package dev.bernouy.cms.feature.website.paramModel.formatting.request;

public class ReqOptionParamModel {

    private String key;
    private String value;
    public ReqOptionParamModel() {}
    public ReqOptionParamModel(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
