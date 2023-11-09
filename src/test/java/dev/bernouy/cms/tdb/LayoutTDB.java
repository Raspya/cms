package dev.bernouy.cms.tdb;

import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.layout.formatting.request.ReqCreateLayout;
import dev.bernouy.cms.feature.website.layout.service.BusinessLogicLayoutService;
import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.page.dto.request.ReqCreatePage;
import dev.bernouy.cms.feature.website.page.service.BusinessLogicPageService;
import dev.bernouy.cms.feature.website.project.Project;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LayoutTDB {

    private BusinessLogicLayoutService layoutService;
    private WebsiteTDB websiteTDB;

    private Project project;
    private String name;

    @Autowired
    public LayoutTDB(BusinessLogicLayoutService layoutService, WebsiteTDB websiteTDB){
        this.layoutService = layoutService;
        this.websiteTDB = websiteTDB;
    }

    public LayoutTDB withProject(Project project){
        this.project = project;
        return this;
    }

    public LayoutTDB withName(String name){
        this.name = name;
        return this;
    }

    public Layout build(){
        if (project == null) project = websiteTDB.build();
        ReqCreateLayout reqCreateLayout = new ReqCreateLayout();
        reqCreateLayout.setProjectId(project.getId());
        reqCreateLayout.setName(name);
        Layout l = layoutService.create(reqCreateLayout, project.getOwner());
        reset();
        return l;
    }

    public void reset(){
        name = "Mon layout " + Math.random();
        project = null;
    }

}
