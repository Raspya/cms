package dev.bernouy.cms.feature.website.component.formatting.request;

public class ReqEditNameComponentDTO {

    private String name;
    private String componentId;

    public ReqEditNameComponentDTO() {
    }

    public ReqEditNameComponentDTO(String name, String componentId) {
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
