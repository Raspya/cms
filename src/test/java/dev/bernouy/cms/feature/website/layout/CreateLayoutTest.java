package dev.bernouy.cms.feature.website.layout;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.website.LayoutTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateLayoutTest extends BaseTest {

    @Test
    public void testCreateLayout() {
        LayoutTDB layoutTDB = new LayoutTDB().build();
        Assertions.assertNotNull(layoutTDB.getId());
    }

}
