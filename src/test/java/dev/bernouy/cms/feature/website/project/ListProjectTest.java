package dev.bernouy.cms.feature.website.project;

import com.fasterxml.jackson.databind.JsonNode;
import dev.bernouy.cms.conf.BaseTest;
import dev.bernouy.cms.tdb.account.AccountTDB;
import dev.bernouy.cms.tdb.website.ProjectTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListProjectTest extends BaseTest {

    @Test
    void listProject(){
        AccountTDB account1 = new AccountTDB().build();
        AccountTDB account2 = new AccountTDB().build();
        ProjectTDB project1 = new ProjectTDB().withAccount(account1).build();
        ProjectTDB project2 = new ProjectTDB().withAccount(account1).build();
        ProjectTDB project3 = new ProjectTDB().withAccount(account2).build();
        JsonNode node = ProjectTDB.getList(account1);
        Assertions.assertEquals(2, node.size());
        node = ProjectTDB.getList(account2);
        Assertions.assertEquals(1, node.size());
    }
}
