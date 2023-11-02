package dev.bernouy.cms.feature.website.layout.formatting.request;

public class ReqSetDefaultLayout {

    private boolean isDefault;

    public ReqSetDefaultLayout() {}
    public ReqSetDefaultLayout(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public boolean getDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
}
