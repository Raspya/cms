package dev.bernouy.cms.feature.website.version.dto.res;

public class ResVersionDTO {

    private String id;
    private String name;
    private String version;

    public ResVersionDTO(String id, String name, String version){
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

}
