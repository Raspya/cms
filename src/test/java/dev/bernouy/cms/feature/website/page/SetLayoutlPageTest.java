package dev.bernouy.cms.feature.website.page;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.tdb.website.LayoutTDB;
import dev.bernouy.cms.tdb.website.PageTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetLayoutlPageTest extends BaseTest {

    @Test
    public void testSetLayoutPage() {
        PageTDB pageTDB = new PageTDB().build();
        Assertions.assertNotNull(pageTDB.getId());

        LayoutTDB layoutTDB = new LayoutTDB().withProject(pageTDB.getProject()).build();
        pageTDB.setLayout(layoutTDB.getId());
        Assertions.assertEquals(layoutTDB.getId(), pageTDB.getLayoutId());
    }


}
