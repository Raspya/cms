package dev.bernouy.cms.feature.website.page.dto.req;

public class ReqPatchLayoutPageDTO {

    private String layoutId;

    public ReqPatchLayoutPageDTO() {}
    public ReqPatchLayoutPageDTO(String layoutId) {
        this.layoutId = layoutId;
    }
    public String getLayoutId() {
        return layoutId;
    }
    public void setLayoutId(String layoutId) {
        this.layoutId = layoutId;
    }
}
