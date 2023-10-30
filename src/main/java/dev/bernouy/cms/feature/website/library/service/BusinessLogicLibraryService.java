package dev.bernouy.cms.feature.website.library.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.AuthWebsiteService;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.library.Library;
import dev.bernouy.cms.feature.website.library.LibraryRepository;
import dev.bernouy.cms.feature.website.library.dto.ReqAddRemoveVersionLibrary;
import dev.bernouy.cms.feature.website.library.dto.ReqCreateLibrary;
import dev.bernouy.cms.feature.website.library.dto.ReqNameLibrary;
import dev.bernouy.cms.feature.website.library.response.LibraryDTO;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.service.BusinessLogicProjectService;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.service.BusinessLogicVersionService;
import dev.bernouy.cms.feature.website.version.service.DataPersistentVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessLogicLibraryService {

    private LibraryRepository libraryRepository;
    private RegexComponent regexComponent;
    private BusinessLogicVersionService versionService;
    private BusinessLogicProjectService projectService;
    private AuthWebsiteService authWebsiteService;
    private DataPersistentVersionService dataPersistentVersionService;


    @Autowired
    public BusinessLogicLibraryService(LibraryRepository repository, RegexComponent regexComponent, BusinessLogicVersionService versionService, BusinessLogicProjectService projectService, AuthWebsiteService authWebsiteService, DataPersistentVersionService dataPersistentVersionService){
        this.libraryRepository = repository;
        this.regexComponent = regexComponent;
        this.versionService = versionService;
        this.projectService = projectService;
        this.authWebsiteService = authWebsiteService;
        this.dataPersistentVersionService = dataPersistentVersionService;
    }


    public Library create(ReqCreateLibrary dto, Account account) {
        projectService.isOwner(dto.getProjectId(), account);
        Project project = projectService.getWebsite( dto.getProjectId() );
        Library library = new Library();
        library.setProject(project);
        library.setLstVersion(new ArrayList<>());
        library.setName(dto.getName());
        libraryRepository.save(library);
        return library;
    }


    public void add(String libraryId, ReqAddRemoveVersionLibrary dto, Account account) {
        Library library = authWebsiteService.isAuthByLibraryAndGetIt(libraryId, account);
        Version version = dataPersistentVersionService.getById(dto.getVersionId());
        ArrayList<Version> lstVersion = getVersionList(libraryId, account);
        for (Version v : lstVersion)
            if (v.getId().equals(version.getId())) throw new BasicException(WebsiteExceptionMessages.INVALID_VERSION_ID);
        lstVersion.add(version);
        library.setLstVersion(lstVersion);
        libraryRepository.save(library);

    }

    public void remove(String libraryId, ReqAddRemoveVersionLibrary dto, Account account) {
        Library library = getById(libraryId, account);
        Version version = versionService.getById(dto.getVersionId());
        ArrayList<Version> lstVersion = getVersionList(libraryId, account);
        for (int i=0 ; i<lstVersion.size() ; i++)
            if (lstVersion.get(i).getId().equals(version.getId())) lstVersion.remove(i);
        library.setLstVersion(lstVersion);
        libraryRepository.save(library);
        throw new BasicException(WebsiteExceptionMessages.INVALID_VERSION_ID);
    }

    public void setName(String libraryId, ReqNameLibrary dto, Account account) {
        regexComponent.isNameValid(dto.getName());
        Library library = getById(libraryId, account);
        library.setName(dto.getName());
        System.out.println(library.getName());
        libraryRepository.save(library);
    }

    public Library getById(String libraryId, Account account) {
        Library library = libraryRepository.findById(libraryId).orElse(null);
        if (library == null) throw new BasicException(WebsiteExceptionMessages.INVALID_LIBRARY_ID);
        authorizeAccount(libraryId, account);
        return library;
    }

    public List<LibraryDTO> listLibrary(Account account, String projectId){
        projectService.isOwner(projectId, account);
        return LibraryDTO.from(libraryRepository.getLibrariesByProjectId(projectId));
    }

    public ArrayList<Version> getVersionList(String libraryId, Account account) {
        Library library = libraryRepository.findById(libraryId).orElse(null);
        if (library == null) throw new BasicException(WebsiteExceptionMessages.INVALID_LIBRARY_ID);
        authorizeAccount(libraryId, account);
        return library.getLstVersion();
    }

    private void authorizeAccount(String libraryId, Account account) {
        Library library = libraryRepository.findById(libraryId).orElse(null);
        if (library == null || !library.getProject().getOwner().equals(account) )
            throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
    }

}
