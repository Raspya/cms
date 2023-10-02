package dev.bernouy.cms.feature.website.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    private LibraryRepository repository;

    @Autowired
    public LibraryService(LibraryRepository repository){
        this.repository = repository;
    }

}
