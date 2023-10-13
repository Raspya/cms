package dev.bernouy.cms.feature.website.layout;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.website.LayoutTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetNameLayoutTest extends BaseTest {

    @Test
    public void testSetNameLayout() {
        LayoutTDB layoutTDB = new LayoutTDB().build();
        Assertions.assertEquals("undefined", layoutTDB.getName());
        layoutTDB.setName("coucou florian");
        Assertions.assertEquals("coucou florian", layoutTDB.getName());
    }

    @Test
    public void testSetInvalidNameLayout() {
        LayoutTDB layoutTDB = new LayoutTDB().build();
        layoutTDB.setName("eau");
        Assertions.assertEquals("undefined", layoutTDB.getName());
    }

}
