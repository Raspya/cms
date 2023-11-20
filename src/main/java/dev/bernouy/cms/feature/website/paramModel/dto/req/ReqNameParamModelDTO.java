package dev.bernouy.cms.feature.website.paramModel.dto.req;

public class ReqNameParamModelDTO {

    private String name;

    public ReqNameParamModelDTO() {}
    public ReqNameParamModelDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
