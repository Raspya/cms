package dev.bernouy.cms.feature.website.layout;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.website.LayoutTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteLayoutTest extends BaseTest {

    @Test
    public void testDeleteLayout() {
        LayoutTDB layoutTDB = new LayoutTDB().build();
        Assertions.assertNotNull(layoutTDB.getId());
        layoutTDB.delete();
        Assertions.assertNull(layoutTDB.getId());
    }

}
