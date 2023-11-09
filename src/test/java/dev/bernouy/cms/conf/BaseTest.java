package dev.bernouy.cms.conf;

import dev.bernouy.cms.common.FileService;
import dev.bernouy.cms.config.properties.FileSystemProperties;
import dev.bernouy.cms.feature.account.AccountService;
import dev.bernouy.cms.feature.website.builder.service.BusinessLogicBuilderService;
import dev.bernouy.cms.feature.website.component.service.BusinessLogicComponentService;
import dev.bernouy.cms.feature.website.layout.service.BusinessLogicLayoutService;
import dev.bernouy.cms.feature.website.library.service.BusinessLogicLibraryService;
import dev.bernouy.cms.feature.website.page.service.BusinessLogicPageService;
import dev.bernouy.cms.feature.website.paramBuilder.service.BusinessLogicParamBuilderService;
import dev.bernouy.cms.feature.website.paramModel.service.ParamModelService;
import dev.bernouy.cms.feature.website.project.service.BusinessLogicProjectService;
import dev.bernouy.cms.feature.website.version.service.BusinessLogicVersionService;
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
    protected AccountService accountService;
    @Autowired
    protected BusinessLogicComponentService componentService;
    @Autowired
    protected BusinessLogicVersionService versionService;
    @Autowired
    protected BusinessLogicParamBuilderService paramBuilderService;
    @Autowired
    protected BusinessLogicProjectService websiteService;
    @Autowired
    protected BusinessLogicPageService pageService;
    @Autowired
    protected BusinessLogicBuilderService builderService;
    @Autowired
    protected BusinessLogicLibraryService libraryService;
    @Autowired
    protected BusinessLogicLayoutService layoutService;
    @Autowired
    protected ParamModelService paramModelService;

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
