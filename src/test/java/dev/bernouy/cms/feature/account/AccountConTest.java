package dev.bernouy.cms.feature.account;

import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.feature.account.dto.request.RegisterConDTO;
import dev.bernouy.cms.tdb.builder.AccountBuilderTDB;
import dev.bernouy.cms.tdb.pojo.AccountTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AccountConTest extends BaseTest {

    @Autowired
    private AccountBuilderTDB builder;

    @Test
    void testCon(){
        AccountTDB account = builder.build();
        RegisterConDTO dto = new RegisterConDTO(account.getEmail(), account.getPassword());
        ResponseEntity<String> response = reqTDB.withDto(dto).send("/api/v1/account/login");
        Assertions.assertFalse(response.getHeaders().get("Set-Cookie").isEmpty());
    }

    @Test
    void testConInvalidEmailOrPassword(){
        AccountTDB account = builder.build();
        RegisterConDTO dto = new RegisterConDTO(account.getEmail(), account.getPassword() + "invalid");
        ResponseEntity<String> response = reqTDB.withDto(dto).send("/api/v1/account/login");
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(response.getBody(), AccountExceptionMessages.ACCOUNT_OR_PASSWORD_DOES_NOT_EXIST);
    }

}
