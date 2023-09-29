package dev.bernouy.cms.feature.website.component.paramModel;

import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.tdb.pojo.AccountTDB;
import dev.bernouy.cms.tdb.pojo.ParamModelTDB;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ParamModelDeleteTest extends BaseTest {

    @Test
    public void deleteParamModel(){
        ParamModelTDB paramModelTDB = this.paramModelBuilderTDB.build();
        Assertions.assertNotNull(paramModelTDB.getId());
        ResponseEntity<String> res = reqTDB.withAuth(paramModelTDB.getVersion().getComponentTDB().getWebsite().getAccount().getCookie())
                .send("/api/v1/component/param/" + paramModelTDB.getId() + "/delete");
        Assertions.assertEquals(HttpStatus.CREATED, res.getStatusCode());
    }
    @Test
    public void deleteInvalidParamModel(){
        ParamModelTDB paramModelTDB = this.paramModelBuilderTDB.build();
        ResponseEntity<String> res1 = reqTDB.withAuth(paramModelTDB.getVersion().getComponentTDB().getWebsite().getAccount().getCookie())
                .send("/api/v1/component/param/" + paramModelTDB.getId() + "/delete");
        ResponseEntity<String> res2 = reqTDB.withAuth(paramModelTDB.getVersion().getComponentTDB().getWebsite().getAccount().getCookie())
                .send("/api/v1/component/param/" + paramModelTDB.getId() + "/delete");
        Assertions.assertEquals(403, res2.getStatusCode().value());
    }

    @Test
    public void deleteWithInvalidAccount(){
        ParamModelTDB paramModelTDB = this.paramModelBuilderTDB.build();
        AccountTDB accountTDB = accountBuilder.build();
        ResponseEntity<String> res = reqTDB.withAuth(accountTDB.getCookie())
                .send("/api/v1/component/param/" + paramModelTDB.getId() + "/delete");
        Assertions.assertEquals(403, res.getStatusCode().value());
    }

}
