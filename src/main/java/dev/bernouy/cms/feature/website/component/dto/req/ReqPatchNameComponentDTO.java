package dev.bernouy.cms.feature.website.component.dto.req;

public class ReqPatchNameComponentDTO {

    private String name;
    private String componentId;

    public ReqPatchNameComponentDTO() {
    }

    public ReqPatchNameComponentDTO(String name, String componentId) {
        this.name = name;
        this.componentId = componentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }
}
