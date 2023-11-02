package dev.bernouy.cms.feature.website.library.formatting.response;

import dev.bernouy.cms.feature.website.library.Library;

import java.util.List;
import java.util.stream.Collectors;

public class LibraryFormatting {

    private String id;
    private String name;

    public LibraryFormatting(String id, String name){
        this.id = id;
        this.name = name;
    }

    public LibraryFormatting() {
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

    public static List<LibraryFormatting> from(List<Library> libraries){
        System.out.println(libraries);
        return libraries.stream().map(library -> new LibraryFormatting(library.getId(), library.getName())).collect(Collectors.toList());
    }

}
