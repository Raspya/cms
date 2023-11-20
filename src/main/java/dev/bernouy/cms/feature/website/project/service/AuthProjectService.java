package dev.bernouy.cms.feature.website.project.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthProjectService {

    private PersistentProjectService dataPersistentProjectService;

    @Autowired
    public AuthProjectService(PersistentProjectService dataPersistentProjectService){
        this.dataPersistentProjectService = dataPersistentProjectService;
    }

    public void checkIsOwner(Project website, Account account ){
        if ( !website.getOwner().equals(account) )
            throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
    }

}
