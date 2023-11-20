package dev.bernouy.cms.feature.website.library;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibraryRepository extends CrudRepository<Library, String> {

    List<Library> getLibrariesByProjectId(String id);
    List<Library> getLibrariesBy(String id);

}
