package dev.bernouy.cms.feature.website.library;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.WebsiteExceptionMessages;
import dev.bernouy.cms.feature.website.library.dto.ReqAddRemoveVersionLibrary;
import dev.bernouy.cms.feature.website.library.dto.ReqCreateLibrary;
import dev.bernouy.cms.feature.website.library.dto.ReqNameLibrary;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.ProjectService;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LibraryService {

    private LibraryRepository libraryRepository;
    private RegexComponent regexComponent;
    private VersionService versionService;
    private ProjectService projectService;


    @Autowired
    public LibraryService(LibraryRepository repository, RegexComponent regexComponent, VersionService versionService, ProjectService projectService){
        this.libraryRepository = repository;
        this.regexComponent = regexComponent;
        this.versionService = versionService;
        this.projectService = projectService;
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
        Library library = getById(libraryId, account);
        Version version = versionService.getById(dto.getVersionId());
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
