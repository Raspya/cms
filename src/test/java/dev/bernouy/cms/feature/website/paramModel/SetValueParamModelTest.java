package dev.bernouy.cms.feature.website.paramModel;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.ParamModelTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetValueParamModelTest extends BaseTest {

    @Test
    public void TestSetValueParamModel() {
        ParamModelTDB paramModelTDB = new ParamModelTDB().build();
        paramModelTDB.setOption("min", "3");
        paramModelTDB.setOption("max", "30");
        paramModelTDB.setValue("Insérez un titre");
        Assertions.assertEquals("Insérez un titre" , paramModelTDB.getValue());
    }

    @Test
    public  void TestSetInvalidMinValueParamModel() {
        ParamModelTDB paramModelTDB = new ParamModelTDB().build();
        paramModelTDB.setOption("min", "20");
        paramModelTDB.setOption("max", "30");
        paramModelTDB.setValue("Insérez un titre");
        Assertions.assertNull(paramModelTDB.getValue());
    }

    @Test
    public  void TestSetInvalidMaxValueParamModel() {
        ParamModelTDB paramModelTDB = new ParamModelTDB().build();
        paramModelTDB.setOption("min", "5");
        paramModelTDB.setOption("max", "15");
        paramModelTDB.setValue("Insérez un titre");
        Assertions.assertNull(paramModelTDB.getValue());
    }

    @Test
    public  void TestSetInvalidMaxMinValueParamModel() {
        ParamModelTDB paramModelTDB = new ParamModelTDB().build();
        paramModelTDB.setOption("min", "17");
        paramModelTDB.setOption("max", "17");
        paramModelTDB.setValue("Insérez un titre");
        Assertions.assertNull(paramModelTDB.getValue());
    }

}
