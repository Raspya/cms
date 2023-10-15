package dev.bernouy.cms.feature.website.builder.dto;

public class ReqPositionBuilder {

    private Integer position;

    public ReqPositionBuilder() {
    }

    public ReqPositionBuilder(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
