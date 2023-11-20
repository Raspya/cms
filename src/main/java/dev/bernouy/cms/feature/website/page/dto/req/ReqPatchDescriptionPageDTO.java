package dev.bernouy.cms.feature.website.page.dto.req;

public class ReqPatchDescriptionPageDTO {

    private String description;

    public ReqPatchDescriptionPageDTO() {}
    public ReqPatchDescriptionPageDTO(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
