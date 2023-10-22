package dev.bernouy.cms.feature.website.paramBuilder.dto;

public class ReqSetValueParamBuilder {

    private String value;

    public ReqSetValueParamBuilder() {}
    public ReqSetValueParamBuilder(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
