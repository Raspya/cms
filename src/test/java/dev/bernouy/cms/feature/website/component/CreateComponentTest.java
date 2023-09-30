package dev.bernouy.cms.feature.website.component;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.ComponentTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateComponentTest extends BaseTest {

    @Test
    public void testCreateComponent() {
        ComponentTDB component = new ComponentTDB().build();
        Assertions.assertNotNull(component.getId());
    }

}