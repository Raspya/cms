package dev.bernouy.cms.feature.website.paramBuilder.dto.req;

public class ReqPatchValueParamBuilderDTO {

    private String value;

    public ReqPatchValueParamBuilderDTO() {}
    public ReqPatchValueParamBuilderDTO(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
