package dev.bernouy.cms.feature.website.layout.dto.req;

public class ReqPatchDefaultLayoutDTO {

    private boolean isDefault;

    public ReqPatchDefaultLayoutDTO() {}
    public ReqPatchDefaultLayoutDTO(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public boolean getDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
}
