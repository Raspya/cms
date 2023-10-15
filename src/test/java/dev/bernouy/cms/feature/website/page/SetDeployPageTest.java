package dev.bernouy.cms.feature.website.page;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.website.PageTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetDeployPageTest extends BaseTest {

    @Test
    public void testSetDeployPage() {
        PageTDB pageTDB = new PageTDB().build();
        Assertions.assertNotNull(pageTDB.getId());
        pageTDB.setTitle("coucou");
        pageTDB.setPublished(true);
        Assertions.assertEquals("coucou", pageTDB.getTitle());
        Assertions.assertTrue(pageTDB.isPublished());
    }

    @Test
    public void testSetInvalidDeployPage() {
        PageTDB pageTDB = new PageTDB().build();
        Assertions.assertNotNull(pageTDB.getId());
        pageTDB.setPublished(true);
        Assertions.assertFalse(pageTDB.isPublished());
    }
}
