package dev.bernouy.cms.feature.website.page.dto;

public class ReqSetDeployPage {

    private boolean isDeploy;

    public ReqSetDeployPage() {}
    public ReqSetDeployPage(boolean isDeploy) {
        this.isDeploy = isDeploy;
    }
    public boolean getDeploy() {
        return isDeploy;
    }

    public void setDeploy(boolean isDeploy) {
        this.isDeploy = isDeploy;
    }
}
