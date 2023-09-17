package dev.bernouy.cms.tdb.builder;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.account.AccountService;
import dev.bernouy.cms.feature.website.project.ProjectService;
import dev.bernouy.cms.tdb.pojo.AccountTDB;
import dev.bernouy.cms.tdb.pojo.ProjectTDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectBuilderTDB {

    private AccountBuilderTDB accountBuilderTDB;
    private ProjectService projectService;
    private AccountService accountService;

    private String name = "Mon application";
    private AccountTDB accountTDB;

    @Autowired
    public ProjectBuilderTDB(AccountService accountService, ProjectService websiteService, AccountBuilderTDB accountBuilderTDB ){
        this.accountService = accountService;
        this.projectService = websiteService;
        this.accountBuilderTDB = accountBuilderTDB;
    }

    private void reset(){
        this.accountTDB = null;
        this.name = "Mon application";
    }

    public ProjectBuilderTDB withName(String name ){
        this.name = name;
        return this;
    }

    public ProjectBuilderTDB withAccount(AccountTDB account){
        this.accountTDB = account;
        return this;
    }

    public ProjectTDB build(){
        if ( this.accountTDB == null )
            this.accountTDB = this.accountBuilderTDB.build();
        Account account = accountService.conByJwt(this.accountTDB.getCookie());
        ProjectTDB tdb = new ProjectTDB();
        tdb.setAccount(this.accountTDB);
        tdb.setName(this.name);
        tdb.setId(projectService.create(tdb.getName(), account).getId());
        reset();
        return tdb;
    }

}
