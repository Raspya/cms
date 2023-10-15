package dev.bernouy.cms.feature.website.page;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.website.PageTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetNamePageTest extends BaseTest {

    @Test
    public void testSetNamePage() {
        PageTDB pageTDB = new PageTDB().build();
        Assertions.assertNotNull(pageTDB.getId());
        pageTDB.setName("coucou");
        Assertions.assertEquals("coucou", pageTDB.getName());
    }

    @Test
    public void testSetInvalidNamePage() {
        PageTDB pageTDB = new PageTDB().build();
        Assertions.assertNotNull(pageTDB.getId());
        pageTDB.setName("ok");
        Assertions.assertEquals("undefined", pageTDB.getName());
    }
}
