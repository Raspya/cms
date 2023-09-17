package dev.bernouy.cms.tdb.builder;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.account.AccountRepository;
import dev.bernouy.cms.feature.account.AccountService;
import dev.bernouy.cms.tdb.pojo.AccountTDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountBuilderTDB {

    private AccountService accountService;
    private AccountRepository accountRepository;

    private int countEmail = 0;
    private String email = "0test@test.fr";
    private String password = "motdepasse";

    @Autowired
    public AccountBuilderTDB( AccountService accountService, AccountRepository accountRepository ){
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    private void reset(){
        this.email = String.valueOf(countEmail) + "test@test.fr";
    }

    public AccountBuilderTDB withEmail(String email) {
        this.email = email;
        return this;
    }

    public AccountBuilderTDB withPassword(String password) {
        this.password = password;
        return this;
    }

    public AccountTDB build() {
        countEmail++;
        accountService.registerAccount(email, password);
        Account account = accountRepository.getAccountByEmail(email);
        String cookie = accountService.con(email, password);
        AccountTDB tdb = new AccountTDB();
        tdb.setEmail(email);
        tdb.setPassword(password);
        tdb.setId(account.getId());
        tdb.setCookie(cookie);
        reset();
        return tdb;
    }

}
