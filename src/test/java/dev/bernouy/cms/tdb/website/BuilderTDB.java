package dev.bernouy.cms.tdb.website;

import dev.bernouy.cms.conf.TDBMother;
import dev.bernouy.cms.feature.website.builder.dto.ReqCreateBuilder;
import dev.bernouy.cms.tdb.account.AccountTDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BuilderTDB extends TDBMother {

    private String id;
    private PageTDB page;
    private LayoutTDB layout;
    private Integer position;
    private VersionTDB component;

    public BuilderTDB build(){
        ResponseEntity<String> res = null;
        ReqCreateBuilder dto = new ReqCreateBuilder();
        if ( this.page == null && this.layout == null ) {
            // this.page = new PageTDB().build();
        } else if ( this.page == null ){
            res = reqTDB.withDto(dto.setLayoutId(layout.getId())).withAuth(getAccount().getCookie()).send("paramBuilder");
        } else {
            res = reqTDB.withDto(dto.setLayoutId(page.getId())).withAuth(getAccount().getCookie()).send("paramBuilder");
        }
        if ( res.getStatusCode() != HttpStatus.CREATED ) return this;
        this.id = res.getBody();
        return this;
    }

    public BuilderTDB withLayout(LayoutTDB layout){
        this.layout = layout;
        return this;
    }

    public BuilderTDB withPage(PageTDB page){
        this.page = page;
        return this;
    }

    public AccountTDB getAccount(){
        if ( page == null && layout == null ) return null;
        //if ( page != null ) return page.getAccount();
        //else return layout.getAccount();
        return null;
    }

}
