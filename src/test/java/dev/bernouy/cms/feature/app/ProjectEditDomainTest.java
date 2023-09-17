package dev.bernouy.cms.feature.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import dev.bernouy.cms.BaseTest;
import dev.bernouy.cms.MethodEnum;
import dev.bernouy.cms.feature.website.project.dto.request.ReqEditDomainWebsiteDTO;
import dev.bernouy.cms.tdb.pojo.AccountTDB;
import dev.bernouy.cms.tdb.pojo.ProjectTDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProjectEditDomainTest extends BaseTest {

    @Test
    void testEditDomainWithUnusedDomain() throws JsonProcessingException {
        ProjectTDB project = projectBuilderTDB.build();
        ReqEditDomainWebsiteDTO dto = new ReqEditDomainWebsiteDTO(project.getId(), "test.fr");
        ResponseEntity<String> response = reqTDB
                .withDto(dto)
                .withAuth(project.getAccount().getCookie())
                .send("/api/v1/project/editDomain");
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ResponseEntity<String> response2 = reqTDB.withAuth(project.getAccount().getCookie()).withMethod(MethodEnum.GET).send("/api/v1/project/list");
        JsonNode jsonNode = objectMapper.readTree(response2.getBody());
        Assertions.assertEquals("test.fr", jsonNode.get(0).get("domain").asText());
    }

    @Test
    void testEditDomainWithAuthAccessTrueUserButIsNotOwner(){
        AccountTDB otherAccount = accountBuilder.build();
        ProjectTDB website = projectBuilderTDB.build();
        ReqEditDomainWebsiteDTO dto = new ReqEditDomainWebsiteDTO(website.getId(), "test.fr");
        ResponseEntity<String> response = reqTDB
                .withDto(dto)
                .withAuth(otherAccount.getCookie())
                .send("/api/v1/project/editDomain");
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

}
