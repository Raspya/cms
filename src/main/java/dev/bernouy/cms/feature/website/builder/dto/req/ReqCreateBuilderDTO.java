package dev.bernouy.cms.feature.website.builder.dto.req;

public class ReqCreateBuilderDTO {

    private String versionId;
    private String layoutId;
    private String pageId;

    public ReqCreateBuilderDTO() {
    }

    public ReqCreateBuilderDTO(String versionId, String layoutId, String pageId) {
        this.versionId = versionId;
        this.layoutId = layoutId;
        this.pageId = pageId;
    }

    public String getLayoutId() {
        return layoutId;
    }

    public ReqCreateBuilderDTO setLayoutId(String layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public String getPageId() {
        return pageId;
    }

    public ReqCreateBuilderDTO setPageId(String pageId) {
        this.pageId = pageId;
        return this;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
}
