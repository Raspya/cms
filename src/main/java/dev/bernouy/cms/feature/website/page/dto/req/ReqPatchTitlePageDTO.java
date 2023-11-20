package dev.bernouy.cms.feature.website.page.dto.req;

public class ReqPatchTitlePageDTO {

    private String title;

    public ReqPatchTitlePageDTO() {}
    public ReqPatchTitlePageDTO(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
