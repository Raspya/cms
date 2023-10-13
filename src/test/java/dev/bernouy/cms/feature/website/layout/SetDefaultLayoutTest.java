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


}
