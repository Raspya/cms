package dev.bernouy.cms.feature.website.component.paramModel;

import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.feature.website.component.dto.ReqKeyParamModel;
import dev.bernouy.cms.feature.website.component.dto.ReqPositionParamModel;
import dev.bernouy.cms.tdb.pojo.AccountTDB;
import dev.bernouy.cms.tdb.pojo.ParamModelTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ParamModelSetPositionTest extends BaseTest {

    @Test
    public void setPositionParamModel() {
        ParamModelTDB paramModelTDB1 = this.paramModelBuilderTDB.build();
        Assertions.assertEquals(1, paramModelTDB1.getPosition());
        ParamModelTDB paramModelTDB2 = this.paramModelBuilderTDB.withVersion(paramModelTDB1.getVersion()).build();
        Assertions.assertEquals(2, paramModelTDB2.getPosition());
        ParamModelTDB paramModelTDB3 = this.paramModelBuilderTDB.withVersion(paramModelTDB1.getVersion()).build();
        Assertions.assertEquals(3, paramModelTDB3.getPosition());

        ReqPositionParamModel dto = new ReqPositionParamModel(1);
        ResponseEntity<String> res = reqTDB.withDto(dto).withAuth(paramModelTDB3.getVersion().getComponentTDB().getWebsite().getAccount().getCookie())
                .send("/api/v1/component/param/" + paramModelTDB3.getId() + "/setPosition");

        System.out.println(res.getBody());
        Assertions.assertEquals(1, paramModelTDB3.getPosition());
        Assertions.assertEquals(2, paramModelTDB1.getPosition());
        Assertions.assertEquals(3, paramModelTDB2.getPosition());
    }


}