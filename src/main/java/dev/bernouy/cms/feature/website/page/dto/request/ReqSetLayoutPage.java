package dev.bernouy.cms.feature.website.page.dto.request;

import dev.bernouy.cms.feature.website.layout.Layout;

public class ReqSetLayoutPage {

    private String layoutId;

    public ReqSetLayoutPage() {}
    public ReqSetLayoutPage(String layoutId) {
        this.layoutId = layoutId;
    }
    public String getLayoutId() {
        return layoutId;
    }
    public void setLayoutId(String layoutId) {
        this.layoutId = layoutId;
    }
}
