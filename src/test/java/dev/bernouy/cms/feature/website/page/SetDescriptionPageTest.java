package dev.bernouy.cms.feature.website.page;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.website.PageTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetDescriptionPageTest extends BaseTest {

    @Test
    public void testSetDescriptionPage() {
        PageTDB pageTDB = new PageTDB().build();
        Assertions.assertNotNull(pageTDB.getId());
        pageTDB.setDescription("coucou");
        Assertions.assertEquals("coucou", pageTDB.getDescription());
    }

}
