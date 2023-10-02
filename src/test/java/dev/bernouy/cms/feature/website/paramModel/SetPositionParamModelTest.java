package dev.bernouy.cms.feature.website.paramModel;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.ParamModelTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetPositionParamModelTest extends BaseTest {

    @Test
    public void testSetPositionParamModel() {
        ParamModelTDB paramModelTDB1 = new ParamModelTDB().build();
        Assertions.assertNotNull(paramModelTDB1.getId());
        Assertions.assertEquals(1, paramModelTDB1.getPosition());
        ParamModelTDB paramModelTDB2 = new ParamModelTDB().withVersion(paramModelTDB1.getVersion()).build();
        Assertions.assertEquals(2, paramModelTDB2.getPosition());
        ParamModelTDB paramModelTDB3 = new ParamModelTDB().withVersion(paramModelTDB1.getVersion()).build();
        Assertions.assertEquals(3, paramModelTDB3.getPosition());
        ParamModelTDB paramModelTDB4 = new ParamModelTDB().withVersion(paramModelTDB1.getVersion()).build();
        ParamModelTDB paramModelTDB5 = new ParamModelTDB().withVersion(paramModelTDB1.getVersion()).build();



        paramModelTDB5.setPosition(1);
        Assertions.assertEquals(1, paramModelTDB5.getPosition());
        Assertions.assertEquals(2, paramModelTDB1.getPosition());
        Assertions.assertEquals(3, paramModelTDB2.getPosition());
        Assertions.assertEquals(4, paramModelTDB3.getPosition());
        Assertions.assertEquals(5, paramModelTDB4.getPosition());

        paramModelTDB3.setPosition(3);
        Assertions.assertEquals(1, paramModelTDB5.getPosition());
        Assertions.assertEquals(2, paramModelTDB1.getPosition());
        Assertions.assertEquals(3, paramModelTDB3.getPosition());
        Assertions.assertEquals(4, paramModelTDB2.getPosition());
        Assertions.assertEquals(5, paramModelTDB4.getPosition());
    }

@Test
    public void testInvalidSetPositionParamModel() {
        ParamModelTDB paramModelTDB1 = new ParamModelTDB().build();
        Assertions.assertNotNull(paramModelTDB1.getId());
        Assertions.assertEquals(1, paramModelTDB1.getPosition());
        ParamModelTDB paramModelTDB2 = new ParamModelTDB().withVersion(paramModelTDB1.getVersion()).build();
        Assertions.assertEquals(2, paramModelTDB2.getPosition());
        ParamModelTDB paramModelTDB3 = new ParamModelTDB().withVersion(paramModelTDB1.getVersion()).build();
        Assertions.assertEquals(3, paramModelTDB3.getPosition());

        paramModelTDB3.setPosition(-1);
        Assertions.assertEquals(3, paramModelTDB3.getPosition());
    }

}
