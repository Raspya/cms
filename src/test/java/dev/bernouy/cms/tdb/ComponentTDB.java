package dev.bernouy.cms.tdb;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.website.component.Component;
import dev.bernouy.cms.feature.website.component.service.BusinessLogicComponentService;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.formatting.request.ReqCreateWebsiteDTO;
import dev.bernouy.cms.feature.website.project.service.BusinessLogicProjectService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComponentTDB {

    private BusinessLogicComponentService componentService;
    private WebsiteTDB websiteTDB;

    private String name;
    private Project project;

    @Autowired
    public ComponentTDB(BusinessLogicComponentService componentService, WebsiteTDB websiteTDB){
        this.componentService = componentService;
        this.websiteTDB = websiteTDB;
    }

    public ComponentTDB withName(String name){
        this.name = name;
        return this;
    }

    public ComponentTDB withProject(Project project){
        this.project = project;
        return this;
    }

    public Component build(){
        if (name == null) name = "Mon composant " + Math.random();
        if (project == null) project = websiteTDB.build();
        Component c = componentService.create(name, project.getId(), project.getOwner());
        reset();
        return c;
    }


    public void reset(){
        name = "Mon composant " + Math.random();
        project = null;
    }

}
