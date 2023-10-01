package dev.bernouy.cms.feature.website.paramModel;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.ParamModelTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetNameParamModelTest extends BaseTest {

    @Test
    public void TestSetNameParamModel() {
        ParamModelTDB paramModelTDB = new ParamModelTDB().build();
        Assertions.assertNotNull(paramModelTDB.getId());
        paramModelTDB.setName("test");
        Assertions.assertEquals("test" , paramModelTDB.getName());
    }

}
