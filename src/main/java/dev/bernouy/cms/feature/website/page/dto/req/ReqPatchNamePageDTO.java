package dev.bernouy.cms.feature.website.page.dto.req;

public class ReqPatchNamePageDTO {

    private String name;

    public ReqPatchNamePageDTO() {}
    public ReqPatchNamePageDTO(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
