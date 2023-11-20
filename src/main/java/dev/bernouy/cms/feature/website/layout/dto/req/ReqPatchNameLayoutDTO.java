package dev.bernouy.cms.feature.website.layout.dto.req;

public class ReqPatchNameLayoutDTO {

    private String name;

    public ReqPatchNameLayoutDTO() {}
    public ReqPatchNameLayoutDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
