package dev.bernouy.cms;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bernouy.cms.common.FileSystem;
import dev.bernouy.cms.tdb.builder.AccountBuilderTDB;
import dev.bernouy.cms.tdb.builder.ComponentBuilderTDB;
import dev.bernouy.cms.tdb.builder.ProjectBuilderTDB;
import dev.bernouy.cms.tdb.builder.VersionBuilderTDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BaseTest {

    @Autowired
    protected MongoTemplate mongoTemplate;
    @Autowired
    protected AccountBuilderTDB accountBuilder;
    @Autowired
    protected ReqTDB reqTDB;
    @Autowired
    protected ProjectBuilderTDB projectBuilderTDB;
    @Autowired
    protected ComponentBuilderTDB componentBuilderTDB;
    @Autowired
    protected VersionBuilderTDB versionBuilderTDB;

    protected ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mongoTemplate.getDb().drop();
        FileSystem.initFileSystem();
    }

    @AfterEach
    public void cleanup() {
    }

}
