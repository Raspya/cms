package dev.bernouy.cms.feature.website.component.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.component.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthComponentService {

    @Autowired
    private PersistentComponentService persistentComponentService;

    public void checkUserAuth(Component component, Account account){
        if ( !component.getProject().getOwner().equals( account ) )
            throw new BasicException(BasicException.AUTH_ERROR, HttpStatus.FORBIDDEN);
    }

}
