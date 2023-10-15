package dev.bernouy.cms.feature.website.page;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.website.PageTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeletePageTest extends BaseTest {

    @Test
    public void testDeletePage() {
        PageTDB pageTDB = new PageTDB().build();
        Assertions.assertNotNull(pageTDB.getId());
        pageTDB.delete();
        Assertions.assertNull(pageTDB.getId());
    }
}
