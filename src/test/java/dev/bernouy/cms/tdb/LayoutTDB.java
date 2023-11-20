package dev.bernouy.cms.tdb;

import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.layout.dto.req.ReqCreateLayoutDTO;
import dev.bernouy.cms.feature.website.layout.service.BusinessLayoutService;
import dev.bernouy.cms.feature.website.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LayoutTDB {

    private BusinessLayoutService layoutService;
    private WebsiteTDB websiteTDB;

    private Project project;
    private String name;

    @Autowired
    public LayoutTDB(BusinessLayoutService layoutService, WebsiteTDB websiteTDB){
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
        ReqCreateLayoutDTO reqCreateLayout = new ReqCreateLayoutDTO();
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
