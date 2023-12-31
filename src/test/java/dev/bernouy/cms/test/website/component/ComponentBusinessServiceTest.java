package dev.bernouy.cms.test.website.component;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.feature.website.component.Component;
import dev.bernouy.cms.feature.website.component.dto.res.ResComponentDTO_VInternalComponent;
import dev.bernouy.cms.feature.website.project.Project;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ComponentBusinessServiceTest extends BaseTest {

    @Test
    void testCreateComponent(){
        Assertions.assertDoesNotThrow(() -> componentTDB.build());
    }

    @Test
    void testEditName(){
        Component component = componentTDB.build();
        Assertions.assertDoesNotThrow(() -> componentService.patchName("Nouveau nom", component.getId(), component.getProject().getOwner()));
    }

    @Test
    void testGetListInternalComponent(){
        Project website = websiteTDB.build();
        Component component1 = componentTDB.withProject(website).build();
        Component component2 = componentTDB.withProject(website).build();
        Component component3 = componentTDB.withProject(website).build();
        List<ResComponentDTO_VInternalComponent> components = componentService.listProjectComponents(website.getId(), website.getOwner());
        Assertions.assertEquals(3, components.size());
        Assertions.assertNotNull(components.get(0).getId());
        Assertions.assertNotNull(components.get(0).getName());
    }

}
