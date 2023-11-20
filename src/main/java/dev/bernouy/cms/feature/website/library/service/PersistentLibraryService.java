package dev.bernouy.cms.feature.website.library.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.exception.MyDatabaseException;
import dev.bernouy.cms.feature.website.library.Library;
import dev.bernouy.cms.feature.website.library.LibraryRepository;
import dev.bernouy.cms.feature.website.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistentLibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    public Library getById(String id){
        Library l = libraryRepository.findById(id).orElse(null);
        if ( l == null ) throw new BasicException("Library not found");
        return l;
    }

    public List<Library> getListByProject(Project project){
        return libraryRepository.getLibrariesByProjectId(project.getId());
    }

    public void save(Library library){
        try{
            libraryRepository.save(library);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

    public void delete(Library library){
        try{
            libraryRepository.delete(library);
        } catch ( Exception e ){
            throw new MyDatabaseException();
        }
    }

}
