package dev.bernouy.cms.feature.website.library.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.library.Library;
import dev.bernouy.cms.feature.website.library.dto.req.ReqAddRemoveVersionLibraryDTO;
import dev.bernouy.cms.feature.website.library.dto.req.ReqCreateLibraryDTO;
import dev.bernouy.cms.feature.website.library.dto.req.ReqNameLibraryDTO;
import dev.bernouy.cms.feature.website.library.dto.response.ResLibraryDTO;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.service.AuthProjectService;
import dev.bernouy.cms.feature.website.project.service.BusinessProjectService;
import dev.bernouy.cms.feature.website.project.service.PersistentProjectService;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.service.PersistentVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessLibraryService {

    private RegexComponent regexComponent;
    private BusinessProjectService projectService;
    private PersistentVersionService dataPersistentVersionService;
    private FormattingLibraryService formattingLibraryService;
    private AuthProjectService authProjectService;
    private PersistentLibraryService persistentLibraryService;
    private PersistentProjectService persistentProjectService;


    @Autowired
    public BusinessLibraryService(PersistentProjectService persistentProjectService, PersistentLibraryService persistentLibraryService, AuthProjectService authProjectService, RegexComponent regexComponent, BusinessProjectService projectService, PersistentVersionService dataPersistentVersionService, FormattingLibraryService dataFormatingLibraryService){
        this.regexComponent = regexComponent;
        this.projectService = projectService;
        this.persistentProjectService = persistentProjectService;
        this.dataPersistentVersionService = dataPersistentVersionService;
        this.formattingLibraryService = dataFormatingLibraryService;
        this.authProjectService = authProjectService;
        this.persistentLibraryService = persistentLibraryService;
    }


    public Library create(ReqCreateLibraryDTO dto, Account account) {
        Project project = persistentProjectService.findById(dto.getProjectId());
        authProjectService.checkIsOwner(project, account);
        Library library = new Library();
        library.setProject(project);
        library.setLstVersion(new ArrayList<>());
        library.setName(dto.getName());
        persistentLibraryService.save(library);
        return library;
    }


    public void add(String libraryId, ReqAddRemoveVersionLibraryDTO dto, Account account) {
        Library library = persistentLibraryService.getById(libraryId);
        authProjectService.checkIsOwner(library.getProject(), account);
        Version version = dataPersistentVersionService.getById(dto.getVersionId());
        List<Version> lstVersion = library.getLstVersion();
        for (Version v : lstVersion)
            if (v.getId().equals(version.getId())) throw new BasicException(WebsiteExceptionMessages.INVALID_VERSION_ID);
        lstVersion.add(version);
        library.setLstVersion(lstVersion);
        persistentLibraryService.save(library);
    }

    public void remove(String libraryId, ReqAddRemoveVersionLibraryDTO dto, Account account) {
        Library library = persistentLibraryService.getById(libraryId);
        authProjectService.checkIsOwner(library.getProject(), account);
        Version version = dataPersistentVersionService.getById(dto.getVersionId());
        List<Version> lstVersion = library.getLstVersion();
        for (int i=0 ; i<lstVersion.size() ; i++)
            if (lstVersion.get(i).getId().equals(version.getId())) lstVersion.remove(i);
        library.setLstVersion(lstVersion);
        persistentLibraryService.save(library);
    }

    public void patchName(String libraryId, ReqNameLibraryDTO dto, Account account) {
        regexComponent.isNameValid(dto.getName());
        Library library = persistentLibraryService.getById(libraryId);
        authProjectService.checkIsOwner(library.getProject(), account);
        library.setName(dto.getName());
        persistentLibraryService.save(library);
    }

    public List<ResLibraryDTO> getLibraryList(String websiteId, Account account) {
        Project website = persistentProjectService.findById(websiteId);
        authProjectService.checkIsOwner(website, account);
        return formattingLibraryService.formatParamModels(persistentLibraryService.getListByProject(website));
    }

    public ResLibraryDTO detail(String libraryId, Account account){
        Library library = persistentLibraryService.getById(libraryId);
        authProjectService.checkIsOwner(library.getProject(), account);
        return formattingLibraryService.formatParamModel(library);
    }

}
