package dev.bernouy.cms.tdb;

import dev.bernouy.cms.conf.TDBMother;
import dev.bernouy.cms.feature.website.project.dto.request.ReqCreateWebsiteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProjectTDB extends TDBMother {

    private static int count = 0;

    private String id = null;
    private String name = "project" + count;
    private String domain = "domain" + count + ".com";
    private AccountTDB account;

    // le build

    public ProjectTDB build(){
        if(!isBuild){
            isBuild = true;
            count++;
        }
        if ( account == null ) account = new AccountTDB().build();
        ReqCreateWebsiteDTO dto = new ReqCreateWebsiteDTO(name);
        ResponseEntity<String> res = reqTDB.withAuth(account.getCookie()).withDto(dto).send("project/create");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.id = res.getBody();
        return this;
    }

    // with pour les setters avant le build

    public ProjectTDB withName(String name) {
        if ( isBuild ) return this;
        this.name = name;
        return this;
    }

    public ProjectTDB withDomain(String domain) {
        if ( isBuild ) return this;
        this.domain = domain;
        return this;
    }

    public ProjectTDB withAccount(AccountTDB account) {
        if ( isBuild ) return this;
        this.account = account;
        return this;
    }
    // set pour les setters après le build


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    public AccountTDB getAccount() {
        return account;
    }

}
