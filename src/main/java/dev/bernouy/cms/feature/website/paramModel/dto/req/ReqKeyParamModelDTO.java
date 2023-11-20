package dev.bernouy.cms.feature.website.paramModel.dto.req;

public class ReqKeyParamModelDTO {

    private String key;

    public ReqKeyParamModelDTO() {}
    public ReqKeyParamModelDTO(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
