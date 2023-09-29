package dev.bernouy.cms.feature.website.component.paramModel;

import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.feature.website.component.dto.ReqEditNameComponentDTO;
import dev.bernouy.cms.feature.website.component.dto.ReqKeyParamModel;
import dev.bernouy.cms.tdb.pojo.AccountTDB;
import dev.bernouy.cms.tdb.pojo.ComponentTDB;
import dev.bernouy.cms.tdb.pojo.ParamModelTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ParamModelSetKeyTest extends BaseTest {

    @Test
    public void setKeyParamModel() {
        ParamModelTDB paramModelTDB = this.paramModelBuilderTDB.build();
        ReqKeyParamModel dto = new ReqKeyParamModel("key");
        ResponseEntity<String> res = reqTDB.withDto(dto).withAuth(paramModelTDB.getVersion().getComponentTDB().getWebsite().getAccount().getCookie())
                .send("/api/v1/component/param/" + paramModelTDB.getId() + "/setKey");
        Assertions.assertEquals(HttpStatus.CREATED, res.getStatusCode());
    }

    @Test
    public void setKeyWithInvalidAccount() {
        ParamModelTDB paramModelTDB = this.paramModelBuilderTDB.build();
        AccountTDB accountTDB = accountBuilder.build();
        ReqKeyParamModel dto = new ReqKeyParamModel("key");
        ResponseEntity<String> res = reqTDB.withDto(dto).withAuth(accountTDB.getCookie())
                .send("/api/v1/component/param/" + paramModelTDB.getId() + "/setKey");
        Assertions.assertEquals(403, res.getStatusCode().value());
    }

    @Test
    public void setKeyWithInvalidFormat() {
        ParamModelTDB paramModelTDB = this.paramModelBuilderTDB.build();
        ReqKeyParamModel dto = new ReqKeyParamModel("CHJAcazjhnd");
        ResponseEntity<String> res = reqTDB.withDto(dto).withAuth(paramModelTDB.getVersion().getComponentTDB().getWebsite().getAccount().getCookie())
                .send("/api/v1/component/param/" + paramModelTDB.getId() + "/setKey");
        Assertions.assertEquals(400, res.getStatusCode().value());
    }
}