package dev.bernouy.cms.feature.website.component.dto.res;

public class ResComponentDTO_VInternalComponent {

    private String id;
    private String name;

    public ResComponentDTO_VInternalComponent() {
    }

    public ResComponentDTO_VInternalComponent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
