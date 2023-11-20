package dev.bernouy.cms.tdb;

import dev.bernouy.cms.feature.website.library.Library;
import dev.bernouy.cms.feature.website.library.dto.req.ReqCreateLibraryDTO;
import dev.bernouy.cms.feature.website.library.service.BusinessLibraryService;
import dev.bernouy.cms.feature.website.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryTDB {

    private BusinessLibraryService libraryService;
    private WebsiteTDB websiteTDB;

    private Project project;
    private String name;

    @Autowired
    public LibraryTDB(BusinessLibraryService libraryService, WebsiteTDB websiteTDB){
        this.libraryService = libraryService;
        this.websiteTDB = websiteTDB;
    }

    public LibraryTDB withProject(Project project){
        this.project = project;
        return this;
    }

    public LibraryTDB withName(String name){
        this.name = name;
        return this;
    }
    
    public Library build(){
        if ( name == null) name = "Ma librairie " + Math.random();
        if (project == null) project = websiteTDB.build();
        ReqCreateLibraryDTO reqCreateLibrary = new ReqCreateLibraryDTO();
        reqCreateLibrary.setProjectId(project.getId());
        reqCreateLibrary.setName(name);
        Library l = libraryService.create(reqCreateLibrary, project.getOwner());
        reset();
        return l;
    }

    public void reset(){
        name = "Ma librairie " + Math.random();
        project = null;
    }

}
