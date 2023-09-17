package dev.bernouy.cms.feature.account;

import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.feature.account.dto.request.RegisterConDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountRegisterTest extends BaseTest {

    @Test
    void testRegisterAndWithAlreadyExistEmail() {
        RegisterConDTO dto = new RegisterConDTO("matt.bernouy@orange.fr", "motdepasse");
        ResponseEntity<String> response = reqTDB.withDto(dto).send("/api/v1/account/register");
        ResponseEntity<String> response2 = reqTDB.withDto(dto).send("/api/v1/account/register");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, response2.getStatusCode());
    }

}
