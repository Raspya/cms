package dev.bernouy.cms.feature.website.paramModel;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.ParamModelTDB;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetOptionParamModelTest extends BaseTest {

    @Test
    public void testSetOptionParamModel() {
        ParamModelTDB paramModelTDB = new ParamModelTDB().build();
        Assertions.assertNotNull(paramModelTDB.getId());
        paramModelTDB.setOption("min", "5");
        paramModelTDB.setOption("max", "15");
        Assertions.assertEquals(5 , paramModelTDB.getOption("min").intValue());
        Assertions.assertEquals(15 , paramModelTDB.getOption("max").intValue());
    }

    @Test
    public void testSetInvalidOptionParamModel() {
        ParamModelTDB paramModelTDB = new ParamModelTDB().build();
        Assertions.assertNotNull(paramModelTDB.getId());
        paramModelTDB.setOption("min", "min");
        paramModelTDB.setOption("max", "1.0");
        Assertions.assertTrue(paramModelTDB.getOption("min").isNull());
        Assertions.assertTrue(paramModelTDB.getOption("max").isNull());
    }

}
