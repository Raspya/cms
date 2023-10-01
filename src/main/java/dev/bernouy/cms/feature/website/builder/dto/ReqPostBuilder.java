package dev.bernouy.cms.feature.website.builder.dto;

public class ReqPostBuilder {

    private String layoutId;
    private String pageId;

    public ReqPostBuilder() {
    }

    public String getLayoutId() {
        return layoutId;
    }

    public ReqPostBuilder setLayoutId(String layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public String getPageId() {
        return pageId;
    }

    public ReqPostBuilder setPageId(String pageId) {
        this.pageId = pageId;
        return this;
    }
}
