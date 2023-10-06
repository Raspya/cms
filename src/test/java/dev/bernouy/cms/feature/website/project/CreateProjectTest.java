package dev.bernouy.cms.feature.website.project;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.account.AccountTDB;
import dev.bernouy.cms.tdb.website.ProjectTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateProjectTest extends BaseTest {

    @Test
    public void testCreateProject() {
        ProjectTDB project = new ProjectTDB().build();
        Assertions.assertNotNull(project.getId());
    }

    @Test
    public void testCreateProjectWithFalseToken(){
        AccountTDB account = new AccountTDB().build();
        account.setForceCookie("falseToken");
        ProjectTDB project = new ProjectTDB().withAccount(account).build();
        Assertions.assertNull(project.getId());
    }

    @Test
    public void testCreateProjectFalseName() {
        ProjectTDB project = new ProjectTDB().withName("a").build();
        Assertions.assertNull(project.getId());
    }

}
