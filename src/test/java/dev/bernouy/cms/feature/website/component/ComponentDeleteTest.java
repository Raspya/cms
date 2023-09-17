package dev.bernouy.cms.feature.website.component;

import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.feature.website.component.dto.ReqDeleteComponentDTO;
import dev.bernouy.cms.tdb.pojo.ComponentTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class ComponentDeleteTest extends BaseTest {

    @Test
    public void deleteWithInvalidComponentId() {
        ComponentTDB component = componentBuilderTDB.build();
        ReqDeleteComponentDTO dto = new ReqDeleteComponentDTO("iddontexist");
        ResponseEntity<String> response = reqTDB.withDto(dto).withAuth(component.getWebsite().getAccount().getCookie()).send("/api/v1/component/delete");
        Assertions.assertEquals(403, response.getStatusCode().value());
    }

    @Test
    public void deleteWithValidComponentValidAccountButNotAuthorized() {
        ComponentTDB component = componentBuilderTDB.build();
        ReqDeleteComponentDTO dto = new ReqDeleteComponentDTO(component.getId());
        ResponseEntity<String> response = reqTDB
                .withDto(dto)
                .withAuth(accountBuilder.build().getCookie())
                .send("/api/v1/component/delete");
        Assertions.assertEquals(403, response.getStatusCode().value());
    }

    @Test
    public void deleteAllValid() {
        ComponentTDB component = componentBuilderTDB.build();
        ReqDeleteComponentDTO dto = new ReqDeleteComponentDTO(component.getId());
        ResponseEntity<String> response = reqTDB.withDto(dto).withAuth(component.getWebsite().getAccount().getCookie()).send("/api/v1/component/delete");
        Assertions.assertEquals(201, response.getStatusCode().value());
    }
}
