package dev.bernouy.cms.conf;

import dev.bernouy.cms.common.FileService;
import dev.bernouy.cms.config.properties.FileSystemProperties;
import dev.bernouy.cms.feature.account.service.AccountBusinessLogicService;
import dev.bernouy.cms.feature.website.builder.service.BusinessBuilderService;
import dev.bernouy.cms.feature.website.component.service.BusinessComponentService;
import dev.bernouy.cms.feature.website.layout.service.BusinessLayoutService;
import dev.bernouy.cms.feature.website.library.service.BusinessLibraryService;
import dev.bernouy.cms.feature.website.page.service.BusinessPageService;
import dev.bernouy.cms.feature.website.paramBuilder.service.BusinessParamBuilderService;
import dev.bernouy.cms.feature.website.paramModel.service.BusinessParamModelService;
import dev.bernouy.cms.feature.website.project.service.BusinessProjectService;
import dev.bernouy.cms.feature.website.version.service.BusinessVersionService;
import dev.bernouy.cms.tdb.*;
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
    protected AccountBusinessLogicService accountService;
    @Autowired
    protected BusinessComponentService componentService;
    @Autowired
    protected BusinessVersionService versionService;
    @Autowired
    protected BusinessParamBuilderService paramBuilderService;
    @Autowired
    protected BusinessProjectService websiteService;
    @Autowired
    protected BusinessPageService pageService;
    @Autowired
    protected BusinessBuilderService builderService;
    @Autowired
    protected BusinessLibraryService libraryService;
    @Autowired
    protected BusinessLayoutService layoutService;
    @Autowired
    protected BusinessParamModelService paramModelService;

    @Autowired
    protected AccountTDB accountTDB;
    @Autowired
    protected ComponentTDB componentTDB;
    @Autowired
    protected VersionTDB versionTDB;
    @Autowired
    protected WebsiteTDB websiteTDB;
    @Autowired
    protected PageTDB pageTDB;
    @Autowired
    protected BuilderTDB builderTDB;
    @Autowired
    protected LibraryTDB libraryTDB;
    @Autowired
    protected LayoutTDB layoutTDB;
    @Autowired
    protected ParamModelTDB paramModelTDB;

    @Autowired
    protected FileSystemProperties fileSystem;

    @BeforeEach
    public void setup() {
        mongoTemplate.getDb().drop();
        FileService.initFileSystem();
    } 

    @AfterEach
    public void cleanup() {
        mongoTemplate.getDb().drop();
    }

}
