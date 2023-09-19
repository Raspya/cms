package dev.bernouy.cms.feature.website.component.component;

import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.feature.website.component.dto.ReqCreateComponentDTO;
import dev.bernouy.cms.tdb.pojo.ProjectTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class ComponentCreateTest extends BaseTest {

    @Test
    public void create() {
        ProjectTDB website = projectBuilderTDB.build();
        ReqCreateComponentDTO dto = new ReqCreateComponentDTO("Mon composant", website.getId());
        ResponseEntity<String> response = reqTDB
                .withDto(dto).withAuth(website.getAccount().getCookie())
                .send("/api/v1/component/create");
        Assertions.assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void createWithoutAuth() {
        ProjectTDB website = projectBuilderTDB.build();
        ReqCreateComponentDTO dto = new ReqCreateComponentDTO("Mon composant", website.getId());
        ResponseEntity<String> response = reqTDB
                .withDto(dto)
                .send("/api/v1/component/create");
        Assertions.assertEquals(403, response.getStatusCode().value());
    }

    @Test
    public void createWithInvalidWebsite() {
        ProjectTDB website = projectBuilderTDB.build();
        ReqCreateComponentDTO dto = new ReqCreateComponentDTO("Mon composant", "0");
        ResponseEntity<String> response = reqTDB
                .withDto(dto).withAuth(website.getAccount().getCookie())
                .send("/api/v1/component/create");
        Assertions.assertEquals(403, response.getStatusCode().value());
    }

    @Test
    public void createWithInvalidName() {
        ProjectTDB website = projectBuilderTDB.build();
        ReqCreateComponentDTO dto = new ReqCreateComponentDTO("", website.getId());
        ResponseEntity<String> response = reqTDB
                .withDto(dto).withAuth(website.getAccount().getCookie())
                .send("/api/v1/component/create");
        Assertions.assertEquals(400, response.getStatusCode().value());
    }

}
