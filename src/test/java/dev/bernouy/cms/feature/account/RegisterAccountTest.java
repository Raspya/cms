package dev.bernouy.cms.feature.account;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.account.AccountTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegisterAccountTest extends BaseTest {

    @Test
    public void testRegisterAccount() {
        AccountTDB account = new AccountTDB().build();
        Assertions.assertNotNull(account.getId());
    }

    @Test
    public void testRegisterAccountAlreadyExist() {
        AccountTDB account1 = new AccountTDB().build();
        AccountTDB account2 = new AccountTDB().withEmail(account1.getEmail()).build();
        Assertions.assertNull(account2.getId());
    }

    @Test
    public void testRegisterAccountInvalidPassword() {
        AccountTDB account = new AccountTDB().withPassword("aze").build();
        Assertions.assertNull(account.getId());
    }

}
