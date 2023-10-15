package dev.bernouy.cms.feature.website.page;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.website.PageTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetUrlPageTest extends BaseTest {

    @Test
    public void testSetUrlPage() {
        PageTDB pageTDB = new PageTDB().build();
        Assertions.assertNotNull(pageTDB.getId());
        pageTDB.setUrl("coucou");
        Assertions.assertEquals("coucou", pageTDB.getUrl());
    }

    @Test
    public void testSetInvalidUrlPage() {
        PageTDB pageTDB = new PageTDB().build();
        Assertions.assertNotNull(pageTDB.getId());
        pageTDB.setUrl("coucou");
        Assertions.assertEquals("coucou", pageTDB.getUrl());

        PageTDB pageTDB2 = new PageTDB().withProject(pageTDB.getProject()).build();
        pageTDB2.setUrl("coucou");
        Assertions.assertEquals("url", pageTDB2.getUrl());
    }

}
