package dev.bernouy.cms.feature.website.paramBuilder.dto.request;

public class ReqCreateParamBuilder {

    private String paramBuilderId;

    public ReqCreateParamBuilder() {}
    public ReqCreateParamBuilder(String paramBuilderId) {
        this.paramBuilderId = paramBuilderId;
    }

    public String getParamBuilderId() {
        return paramBuilderId;
    }

    public void setParamBuilderId(String paramBuilderId) {
        this.paramBuilderId = paramBuilderId;
    }
}
