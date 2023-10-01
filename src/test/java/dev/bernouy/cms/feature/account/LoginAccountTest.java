package dev.bernouy.cms.feature.account;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.account.AccountTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginAccountTest extends BaseTest {

    @Test
    void testLoginTrueCredentials(){
        AccountTDB account = new AccountTDB().build();
        boolean b = account.login(account.getEmail(), account.getPassword());
        Assertions.assertTrue(b);
    }

    @Test
    void testLoginTrueEmailFalsePassword(){
        AccountTDB account = new AccountTDB().build();
        boolean b = account.login(account.getEmail(), "fauxMotDEPass16!");
        Assertions.assertFalse(b);
    }

}
