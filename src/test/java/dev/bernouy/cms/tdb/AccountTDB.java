package dev.bernouy.cms.tdb;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.account.AccountService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountTDB {

    private AccountService accountService;

    private int cpt = 0;
    private String email;
    private String password;

    @Autowired
    public AccountTDB(AccountService accountService){
        this.accountService = accountService;
    }

    public AccountTDB withEmail(String email){
        this.email = email;
        return this;
    }

    public AccountTDB withPassword(String password){
        this.password = password;
        return this;
    }

    public Account build(){
        cpt++;
        if (email == null) email = "test"+cpt+"@gmail.com";
        if (password == null ) password = "Test76930!";
        Account a = accountService.registerAccount(email, password);
        reset();
        return a;
    }

    public void reset(){
        cpt++;
        email = "test"+cpt+"@gmail.com";
        password = "Test76930!";
    }

}
