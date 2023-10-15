package dev.bernouy.cms.feature.website.page;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.website.PageTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreatePageTest extends BaseTest {

    @Test
    public void testCreatePage() {
        PageTDB pageTDB = new PageTDB().build();
        Assertions.assertNotNull(pageTDB.getId());
        Assertions.assertEquals("undefined", pageTDB.getName());
        Assertions.assertEquals("url", pageTDB.getUrl());
    }
}
