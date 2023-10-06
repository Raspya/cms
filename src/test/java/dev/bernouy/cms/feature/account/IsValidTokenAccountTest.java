package dev.bernouy.cms.feature.account;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.account.AccountTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsValidTokenAccountTest extends BaseTest {

    @Test
    void testIsValidTokenWithValidToken(){
        AccountTDB account = new AccountTDB().build();
        Assertions.assertTrue(account.isValidToken(account.getCookie()));
    }

    @Test
    void testIsValidTokenWithInvalidToken(){
        AccountTDB account = new AccountTDB().build();
        Assertions.assertFalse(account.isValidToken("falseToken"));
    }

}
