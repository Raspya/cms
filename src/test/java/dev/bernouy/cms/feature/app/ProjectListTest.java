package dev.bernouy.cms.feature.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.MethodEnum;
import dev.bernouy.cms.tdb.pojo.ProjectTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class ProjectListTest extends BaseTest {


    @Test
    void testGetListWithTwoWebsitesDifferentAccount() throws JsonProcessingException {
        ProjectTDB website = projectBuilderTDB.build();
        ProjectTDB website2 = projectBuilderTDB.build();
        ResponseEntity<String> response = reqTDB.withAuth(website.getAccount().getCookie()).withMethod(MethodEnum.GET).send("/api/v1/project/list");
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        Assertions.assertEquals(1, jsonNode.size());
    }

}
