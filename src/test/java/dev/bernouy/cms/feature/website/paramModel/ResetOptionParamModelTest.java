package dev.bernouy.cms.feature.website.paramModel;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.ParamModelTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResetOptionParamModelTest extends BaseTest {

    @Test
    public void testResetOptionParamModel() {
        ParamModelTDB paramModelTDB = new ParamModelTDB().build();
        Assertions.assertNotNull(paramModelTDB.getId());
        paramModelTDB.setOption("min", "5");
        paramModelTDB.setOption("max", "15");
        Assertions.assertEquals(5 , paramModelTDB.getOption("min").intValue());
        Assertions.assertEquals(15 , paramModelTDB.getOption("max").intValue());
        paramModelTDB.resetOption("min");
        Assertions.assertTrue( paramModelTDB.getOption("min").isNull());
        paramModelTDB.resetOption("max");
        Assertions.assertTrue( paramModelTDB.getOption("max").isNull());

    }

    @Test
    public void testResetAllOptionParamModel() {
        ParamModelTDB paramModelTDB = new ParamModelTDB().build();
        Assertions.assertNotNull(paramModelTDB.getId());
        paramModelTDB.setOption("min", "5");
        paramModelTDB.setOption("max", "15");
        Assertions.assertEquals(5 , paramModelTDB.getOption("min").intValue());
        Assertions.assertEquals(15 , paramModelTDB.getOption("max").intValue());
        paramModelTDB.resetOptions();
        Assertions.assertTrue( paramModelTDB.getOption("min").isNull());
        Assertions.assertTrue( paramModelTDB.getOption("max").isNull());
    }

}
