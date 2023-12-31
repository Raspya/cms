package dev.bernouy.cms.feature.website.library.dto.response;

import dev.bernouy.cms.feature.website.library.Library;

import java.util.List;
import java.util.stream.Collectors;

public class ResLibraryDTO {

    private String id;
    private String name;

    public ResLibraryDTO(String id, String name){
        this.id = id;
        this.name = name;
    }

    public ResLibraryDTO() {
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

    public static List<ResLibraryDTO> from(List<Library> libraries){
        System.out.println(libraries);
        return libraries.stream().map(library -> new ResLibraryDTO(library.getId(), library.getName())).collect(Collectors.toList());
    }

}
