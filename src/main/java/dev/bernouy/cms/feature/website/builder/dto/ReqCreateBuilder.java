package dev.bernouy.cms.feature.website.builder.dto;

public class ReqCreateBuilder {

    private String versionId;
    private String layoutId;
    private String pageId;

    public ReqCreateBuilder() {
    }

    public ReqCreateBuilder(String versionId, String layoutId, String pageId) {
        this.versionId = versionId;
        this.layoutId = layoutId;
        this.pageId = pageId;
    }

    public String getLayoutId() {
        return layoutId;
    }

    public ReqCreateBuilder setLayoutId(String layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public String getPageId() {
        return pageId;
    }

    public ReqCreateBuilder setPageId(String pageId) {
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
