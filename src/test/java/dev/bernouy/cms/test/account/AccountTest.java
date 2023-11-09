package dev.bernouy.cms.test.account;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.tdb.PageTDB;
import dev.bernouy.cms.tdb.WebsiteTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountTest extends BaseTest {

    @Test
    public void testAccountCreation(){
        Account account = Assertions.assertDoesNotThrow(() -> accountTDB.build());
        Assertions.assertNotNull(account.getId());
    }

    @Test
    void testLogin(){
        Account account = Assertions.assertDoesNotThrow(() -> accountTDB.withPassword("Test76930!").build());
        Assertions.assertDoesNotThrow(() -> accountService.con(account.getEmail(), "Test76930!"));
        Assertions.assertNotNull(accountService.con(account.getEmail(), "Test76930!"));
    }

    @Test
    void testLoginViaJwt(){
        Account account = Assertions.assertDoesNotThrow(() -> accountTDB.withPassword("Test76930!").build());
        String token = accountService.con(account.getEmail(), "Test76930!");
        Assertions.assertDoesNotThrow(() -> accountService.conByJwt(token));
        Assertions.assertEquals(account.getId(), accountService.conByJwt(token).getId());
    }

}
