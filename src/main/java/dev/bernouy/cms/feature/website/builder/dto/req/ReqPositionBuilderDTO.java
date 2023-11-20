package dev.bernouy.cms.feature.website.builder.dto.req;

public class ReqPositionBuilderDTO {

    private Integer position;

    public ReqPositionBuilderDTO() {
    }

    public ReqPositionBuilderDTO(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
