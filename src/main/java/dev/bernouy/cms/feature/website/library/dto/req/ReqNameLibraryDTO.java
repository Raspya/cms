package dev.bernouy.cms.feature.website.library.dto.req;

public class ReqNameLibraryDTO {

    private String name;

    public ReqNameLibraryDTO() {}
    public ReqNameLibraryDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
