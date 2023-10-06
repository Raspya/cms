package dev.bernouy.cms.feature.website.paramModel.dto;

public class ReqPositionParamModel {

    private int position;

    public ReqPositionParamModel() {}
    public ReqPositionParamModel(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
