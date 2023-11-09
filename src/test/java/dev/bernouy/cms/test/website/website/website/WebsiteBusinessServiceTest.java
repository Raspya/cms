package dev.bernouy.cms.test.website.website.website;


import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.formatting.response.ProjectFormatting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

public class WebsiteBusinessServiceTest extends BaseTest {


    @Test
    void testCreateWebsite(){
        Project website = Assertions.assertDoesNotThrow(() -> websiteTDB.build());
        Assertions.assertNotNull(website.getId());
    }

    @Test
    void testIsOwnerMethod(){
        Account account = accountTDB.build();
        Project website = Assertions.assertDoesNotThrow(() -> websiteTDB.build());
        Assertions.assertDoesNotThrow(() -> websiteService.isOwner(website.getId(), website.getOwner()));
        Assertions.assertThrows(BasicException.class, () -> websiteService.isOwner(website.getId(), account));
    }

    @Test
    void testGetListWebsite(){
        Account account = accountTDB.build();
        Project website1 = websiteTDB.withAccount(account).build();
        Project website2 = websiteTDB.withAccount(account).build();
        Project website3 = websiteTDB.withAccount(account).build();
        Project website4 = websiteTDB.build();
        List<ProjectFormatting> websites = websiteService.getListWebsite(account);
        Assertions.assertEquals(3, websites.size());
        Assertions.assertNotNull(websites.get(0).getId());
        Assertions.assertNotNull(websites.get(0).getDomain());
        Assertions.assertNotNull(websites.get(0).getName());
    }

    @Test
    void testPatchDomain(){
        Project website = websiteTDB.build();
        websiteTDB.withDomain("mon-url.com").build();
        Assertions.assertDoesNotThrow(() -> websiteService.editDomain("nouveau-nom.fr", website.getId(), website.getOwner()));
        Assertions.assertThrows(Exception.class, () -> websiteService.editDomain("mon-url.com", website.getId(), website.getOwner()));
    }

}
