package dev.bernouy.cms.tdb;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.account.AccountService;
import dev.bernouy.cms.feature.website.project.Project;
import dev.bernouy.cms.feature.website.project.formatting.request.ReqCreateWebsiteDTO;
import dev.bernouy.cms.feature.website.project.service.BusinessLogicProjectService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

@Service
public class WebsiteTDB {

    private BusinessLogicProjectService projectService;
    private AccountTDB accountTDB;

    private int cpt = 0;
    private String domain;
    private String name;
    private Account account;

    @Autowired
    public WebsiteTDB(BusinessLogicProjectService projectService, AccountTDB accountTDB){
        this.projectService = projectService;
        this.accountTDB = accountTDB;
    }

    public WebsiteTDB withDomain(String domain){
        this.domain = domain;
        return this;
    }

    public WebsiteTDB withName(String name){
        this.name = name;
        return this;
    }

    public WebsiteTDB withAccount(Account account){
        this.account = account;
        return this;
    }

    public Project build(){
        cpt++;
        if (account == null) account = accountTDB.build();
        if (domain == null) domain = "mon-url" + cpt + ".com";
        if (name == null) name = "Mon site " + Math.random();
        ReqCreateWebsiteDTO reqCreateWebsiteDTO = new ReqCreateWebsiteDTO();
        reqCreateWebsiteDTO.setDomain(domain);
        reqCreateWebsiteDTO.setName(name);
        Project p = projectService.create(reqCreateWebsiteDTO, account);
        reset();
        return p;
    }

    public void reset(){
        cpt++;
        domain = "mon-url" + cpt + ".com";
        name = "Mon site " + Math.random();
        account = null;
    }

}
