package dev.bernouy.cms.feature.website.component.dto;

public class PostBuilder {

    private String layoutId;
    private String pageId;

    public PostBuilder() {
    }

    public String getLayoutId() {
        return layoutId;
    }

    public PostBuilder setLayoutId(String layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public String getPageId() {
        return pageId;
    }

    public PostBuilder setPageId(String pageId) {
        this.pageId = pageId;
        return this;
    }
}
