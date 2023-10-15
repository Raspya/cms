package dev.bernouy.cms.feature.website.layout;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.website.LayoutTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetDefaultLayoutTest extends BaseTest {

    @Test
    public void testSetDefaultLayout() {
        LayoutTDB layoutTDB = new LayoutTDB().build();
        Assertions.assertFalse(layoutTDB.isDefault());
        layoutTDB.setDefault(true);
        Assertions.assertTrue(layoutTDB.isDefault());
    }

    @Test
    public void testSetAnotherDefaultLayout() {
        LayoutTDB layoutTDB = new LayoutTDB().build();
        Assertions.assertFalse(layoutTDB.isDefault());
        layoutTDB.setDefault(true);
        Assertions.assertTrue(layoutTDB.isDefault());

        LayoutTDB layoutTDB2 = new LayoutTDB().withProject(layoutTDB.getProject()).build();
        layoutTDB2.setDefault(true);
        Assertions.assertTrue(layoutTDB2.isDefault());
        Assertions.assertFalse(layoutTDB.isDefault());
    }


}
