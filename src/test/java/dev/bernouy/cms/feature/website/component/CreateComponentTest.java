package dev.bernouy.cms.feature.website.component;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.website.ComponentTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateComponentTest extends BaseTest {

    @Test
    public void testCreateComponent() {
        ComponentTDB component = new ComponentTDB().build();
        Assertions.assertNotNull(component.getId());
    }

    @Test void testCreateComponentInvalidName(){
        ComponentTDB component = new ComponentTDB().withName("a").build();
        Assertions.assertNull(component.getId());
    }

}
