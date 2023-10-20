package dev.bernouy.cms.feature.website.library.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.feature.website.library.Library;
import dev.bernouy.cms.feature.website.library.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataPersistentLibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    public Library getById(String id){
        Library l = libraryRepository.findById(id).orElse(null);
        if ( l == null ) throw new BasicException("Library not found");
        return l;
    }

}
