package dev.bernouy.cms.feature.website.component.component;

import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.feature.website.component.dto.ReqEditNameComponentDTO;
import dev.bernouy.cms.tdb.pojo.AccountTDB;
import dev.bernouy.cms.tdb.pojo.ComponentTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class ComponentEditNameTest extends BaseTest {

    @Test
    public void editNameWithInvalidComponentId() {
        ComponentTDB component = componentBuilderTDB.build();
        ReqEditNameComponentDTO dto = new ReqEditNameComponentDTO("Mon composant", "iddontexist");
        ResponseEntity<String> response = reqTDB.withDto(dto).withAuth(component.getWebsite().getAccount().getCookie()).send("/api/v1/component/editName");
        Assertions.assertEquals(403, response.getStatusCode().value());
    }

    @Test
    public void editNameWithValidComponentValidAccountButNotAuthorized() {
        ComponentTDB component = componentBuilderTDB.build();
        AccountTDB account = accountBuilder.build();
        ReqEditNameComponentDTO dto = new ReqEditNameComponentDTO("Mon composant", component.getId());
        ResponseEntity<String> response = reqTDB
                .withDto(dto)
                .withAuth(account.getCookie())
                .send("/api/v1/component/editName");
        Assertions.assertEquals(403, response.getStatusCode().value());
    }

    @Test
    public void editNameAllValid() {
        ComponentTDB component = componentBuilderTDB.build();
        ReqEditNameComponentDTO dto = new ReqEditNameComponentDTO("Mon composant", component.getId());
        ResponseEntity<String> response = reqTDB.withDto(dto).withAuth(component.getWebsite().getAccount().getCookie()).send("/api/v1/component/editName");
        Assertions.assertEquals(201, response.getStatusCode().value());
    }

}
