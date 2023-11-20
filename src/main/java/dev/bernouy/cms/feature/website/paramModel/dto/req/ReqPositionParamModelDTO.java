package dev.bernouy.cms.feature.website.paramModel.dto.req;

public class ReqPositionParamModelDTO {

    private int position;

    public ReqPositionParamModelDTO() {}
    public ReqPositionParamModelDTO(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
