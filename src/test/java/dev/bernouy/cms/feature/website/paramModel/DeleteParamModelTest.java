package dev.bernouy.cms.feature.website.paramModel;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.ParamModelTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteParamModelTest extends BaseTest {

    @Test
    public void testDeleteParamModel() {
        ParamModelTDB paramModelTDB = new ParamModelTDB().build();
        Assertions.assertNotNull(paramModelTDB.getId());
        paramModelTDB.delete();
        Assertions.assertNull(paramModelTDB.getId());
    }

    @Test
    public void testInvalidDeleteParamModel() {
        ParamModelTDB paramModelTDB = new ParamModelTDB().build();
        Assertions.assertNotNull(paramModelTDB.getId());
        paramModelTDB.delete();
        Assertions.assertNull(paramModelTDB.getId());
        paramModelTDB.delete();
        Assertions.assertNull(paramModelTDB.getId());
    }

}
