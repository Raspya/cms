package dev.bernouy.cms.feature.website.paramModel;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.ParamModelTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetKeyParamModelTest extends BaseTest {

    @Test
    public void TestSetKeyParamModel() {
        ParamModelTDB paramModelTDB = new ParamModelTDB().build();
        Assertions.assertNotNull(paramModelTDB.getId());
        paramModelTDB.setKey("test");
        Assertions.assertEquals("test" , paramModelTDB.getKey());
    }

    @Test
    public void test2() {
        ParamModelTDB paramModelTDB = new ParamModelTDB().build();
        Assertions.assertNotNull(paramModelTDB.getId());
        paramModelTDB.setKey("ok");
        Assertions.assertNull(paramModelTDB.getKey());
    }

}
