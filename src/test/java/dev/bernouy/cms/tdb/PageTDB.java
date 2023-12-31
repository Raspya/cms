package dev.bernouy.cms.tdb;

import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.page.dto.req.ReqCreatePage;
import dev.bernouy.cms.feature.website.page.service.BusinessPageService;
import dev.bernouy.cms.feature.website.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageTDB {

    private BusinessPageService pageService;
    private WebsiteTDB websiteTDB;

    private int cpt = 0;
    private Project project;
    private String name;
    private String path;

    @Autowired
    public PageTDB(BusinessPageService pageService, WebsiteTDB websiteTDB){
        this.pageService = pageService;
        this.websiteTDB = websiteTDB;
    }

    public PageTDB withProject(Project project){
        this.project = project;
        return this;
    }

    public PageTDB withName(String name){
        this.name = name;
        return this;
    }


    public PageTDB withPath(String path){
        this.path = path;
        return this;
    }

    public Page build(){
        if ( name == null) name = "Ma page " + Math.random();
        if ( path == null) path = "/mon-chemin" + cpt;
        if (project == null) project = websiteTDB.build();
        ReqCreatePage reqCreatePage = new ReqCreatePage();
        reqCreatePage.setProjectId(project.getId());
        reqCreatePage.setPath(path);
        reqCreatePage.setName(name);
        Page p = pageService.create(reqCreatePage, project.getOwner());
        reset();
        return p;
    }

    public void reset(){
        cpt++;
        name = "Ma page " + Math.random();
        project = null;
        path = "/mon-chemin" + cpt;
    }

}
