package dev.bernouy.cms.feature.website.paramBuilder.dto.req;

public class ReqCreateParamBuilderDTO {

    private String paramBuilderId;

    public ReqCreateParamBuilderDTO() {}
    public ReqCreateParamBuilderDTO(String paramBuilderId) {
        this.paramBuilderId = paramBuilderId;
    }

    public String getParamBuilderId() {
        return paramBuilderId;
    }

    public void setParamBuilderId(String paramBuilderId) {
        this.paramBuilderId = paramBuilderId;
    }
}
