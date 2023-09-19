package dev.bernouy.cms.feature.website.project;

import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.MethodEnum;
import dev.bernouy.cms.feature.website.project.dto.request.ReqCreateWebsiteDTO;
import dev.bernouy.cms.tdb.pojo.AccountTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProjectCreateTest extends BaseTest {

    @Test
    void testCreateWebsiteWithValidAccount(){
        AccountTDB account = accountBuilder.build();
        ReqCreateWebsiteDTO dto = new ReqCreateWebsiteDTO("Mon application");
        ResponseEntity<String> response = reqTDB.withDto(dto).withAuth(account.getCookie()).send("/api/v1/project/create");
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ResponseEntity<String> response2 = reqTDB.withAuth(account.getCookie()).withMethod(MethodEnum.GET).send("/api/v1/website/list");
        Assertions.assertNotEquals(response2.getBody(), "[]");
    }

    @Test
    void testCreateWebsiteWithoutCookieAuth(){
        ReqCreateWebsiteDTO dto = new ReqCreateWebsiteDTO("Mon application");
        ResponseEntity<String> response = reqTDB.withDto(dto).send("/api/v1/project/create");
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

}
