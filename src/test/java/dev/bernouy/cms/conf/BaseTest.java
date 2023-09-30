package dev.bernouy.cms.conf;

import dev.bernouy.cms.common.FileSystem;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BaseTest {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @BeforeEach
    public void setup() {
        mongoTemplate.getDb().drop();
        FileSystem.initFileSystem();
    }

    @AfterEach
    public void cleanup() {
    }

}
