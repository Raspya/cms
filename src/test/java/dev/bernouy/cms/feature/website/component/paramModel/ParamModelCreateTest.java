package dev.bernouy.cms.feature.website.component.paramModel;

import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.tdb.pojo.ParamModelTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParamModelCreateTest extends BaseTest {

    @Test
    public void createParamModel(){
        ParamModelTDB paramModelTDB = this.paramModelBuilderTDB.build();
        Assertions.assertNotNull(paramModelTDB.getId());
    }

    @Test
    public void createParamModelWithInvalidType(){
        ParamModelTDB paramModelTDB = this.paramModelBuilderTDB.withType("invalid").build();
        Assertions.assertNull(paramModelTDB.getId());
    }

}
