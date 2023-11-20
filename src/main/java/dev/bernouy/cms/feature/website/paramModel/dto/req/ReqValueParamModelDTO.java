package dev.bernouy.cms.feature.website.paramModel.dto.req;

public class ReqValueParamModelDTO {

    private String value;

    public ReqValueParamModelDTO() {}
    public ReqValueParamModelDTO(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public void setName(String value) {
        this.value = value;
    }

}