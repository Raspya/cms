package dev.bernouy.cms.feature.website;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.library.Library;
import dev.bernouy.cms.feature.website.library.service.DataPersistentLibraryService;
import dev.bernouy.cms.feature.website.version.Version;
import dev.bernouy.cms.feature.website.version.service.DataPersistentVersionService;
import org.springframework.stereotype.Service;

@Service
public class AuthWebsiteService {

    private DataPersistentVersionService dataPersistentVersionService;
    private DataPersistentLibraryService dataPersistentLibraryService;

    public AuthWebsiteService(DataPersistentVersionService dataPersistentVersionService, DataPersistentLibraryService dataPersistentLibraryService){
        this.dataPersistentVersionService = dataPersistentVersionService;
        this.dataPersistentLibraryService = dataPersistentLibraryService;
    }

    public Version isAuthByVersionAndGetIt(String versionId, Account account){
        Version v = dataPersistentVersionService.getById(versionId);
        if ( !v.getComponent().getProject().getOwner().equals(account) )
            throw new BasicException("You are not authorized to access this version");
        return v;
    }

    public Library isAuthByLibraryAndGetIt(String libraryId, Account account){
        Library l = dataPersistentLibraryService.getById(libraryId);
        if ( !l.getProject().getOwner().equals(account) )
            throw new BasicException("You are not authorized to access this library");
        return l;
    }
}
