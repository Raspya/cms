package dev.bernouy.cms.feature.website.project;

import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.account.AccountTDB;
import dev.bernouy.cms.tdb.website.ProjectTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EditDomainProjectTest extends BaseTest {

    @Test
    void testEditDomainProject() {
        ProjectTDB project = new ProjectTDB().build();
        project.setDomain("new-domain.com");
        Assertions.assertEquals("new-domain.com", project.getDomain());
    }

    @Test
    void testEditDomainProjectInvalidDomainName() {
        ProjectTDB project = new ProjectTDB().build();
        project.setDomain("new_d0Domain.com");
        Assertions.assertNotEquals("new_d0Domain.com", project.getDomain());
    }

    @Test
    void testTryEditDomainNotAuth() {
        AccountTDB accountTDB = new AccountTDB().build();
        ProjectTDB project = new ProjectTDB().build();
        project.setForceAccount(accountTDB);
        project.setDomain("new-domain01.com");
        Assertions.assertNotEquals("new-domain01.com", project.getDomain());
    }
}
