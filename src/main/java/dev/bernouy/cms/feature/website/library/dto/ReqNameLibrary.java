package dev.bernouy.cms.feature.website.library.dto;

public class ReqNameLibrary {

    private String name;

    public ReqNameLibrary() {}
    public ReqNameLibrary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
