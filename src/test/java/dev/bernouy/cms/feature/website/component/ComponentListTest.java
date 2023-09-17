package dev.bernouy.cms.feature.website.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.MethodEnum;
import dev.bernouy.cms.tdb.pojo.ComponentTDB;
import dev.bernouy.cms.tdb.pojo.ProjectTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class ComponentListTest extends BaseTest {

    @Test
    public void testIfListContainsAllComponents() throws JsonProcessingException {
        ProjectTDB websiteTDB = projectBuilderTDB.build();
        ComponentTDB component1 = componentBuilderTDB.withWebsite(websiteTDB).build();
        ComponentTDB component2 = componentBuilderTDB.withWebsite(websiteTDB).build();
        ComponentTDB component3 = componentBuilderTDB.withWebsite(websiteTDB).build();
        ComponentTDB componentTDB = componentBuilderTDB.build();
        ResponseEntity<String> response = reqTDB.withMethod(MethodEnum.GET).withAuth(websiteTDB.getAccount().getCookie()).send("/api/v1/component/list/" + websiteTDB.getId());
        Assertions.assertEquals(200, response.getStatusCode().value());
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        Assertions.assertEquals(3, jsonNode.size());
    }

}
