package dev.bernouy.cms.feature.website.layout.dto.res;

public class ResLayoutDTO {

    private String id;
    private String name;
    private Boolean isDefault;

    public ResLayoutDTO(String id, String name, Boolean isDefault) {
        this.id = id;
        this.name = name;
        this.isDefault = isDefault;
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

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
}
