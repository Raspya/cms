package dev.bernouy.cms.feature.website.page.dto.req;

public class ReqPatchDeployPageDTO {

    private boolean isDeploy;

    public ReqPatchDeployPageDTO() {}
    public ReqPatchDeployPageDTO(boolean isDeploy) {
        this.isDeploy = isDeploy;
    }
    public boolean getDeploy() {
        return isDeploy;
    }

    public void setDeploy(boolean isDeploy) {
        this.isDeploy = isDeploy;
    }
}
