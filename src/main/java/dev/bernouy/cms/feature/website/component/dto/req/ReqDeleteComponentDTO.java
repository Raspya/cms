package dev.bernouy.cms.feature.website.component.dto.req;

public class ReqDeleteComponentDTO {

    private String componentId;

    public ReqDeleteComponentDTO() {
    }

    public ReqDeleteComponentDTO(String componentId) {
        this.componentId = componentId;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }
}
