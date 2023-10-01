package dev.bernouy.cms.feature.website.paramModel;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.ParamModelTDB;
import dev.bernouy.cms.tdb.VersionTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateParamModelTest extends BaseTest {

    @Test
    public void testCreateParamModel() {
        ParamModelTDB paramModelTDB = new ParamModelTDB().build();
        Assertions.assertNotNull(paramModelTDB.getId());
    }

    @Test
    public void testCreateParamModelInvalidType() {
        ParamModelTDB paramModelTDB = new ParamModelTDB().withType("MATTHEW").build();
        Assertions.assertNull(paramModelTDB.getId());
    }

}
