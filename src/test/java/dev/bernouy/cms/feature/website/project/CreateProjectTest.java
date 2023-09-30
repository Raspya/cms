package dev.bernouy.cms.feature.website.project;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.ProjectTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateProjectTest extends BaseTest {

    @Test
    public void testCreateProject() {
        ProjectTDB project = new ProjectTDB().build();
        Assertions.assertNotNull(project.getId());
    }
}
