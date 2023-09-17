package dev.bernouy.cms.tdb.builder;

import dev.bernouy.cms.ReqTDB;
import dev.bernouy.cms.feature.website.component.service.ComponentService;
import dev.bernouy.cms.feature.website.component.dto.ReqCreateComponentDTO;
import dev.bernouy.cms.tdb.pojo.ComponentTDB;
import dev.bernouy.cms.tdb.pojo.ProjectTDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@org.springframework.stereotype.Component
public class ComponentBuilderTDB{

    private ComponentService componentService;
    private ProjectBuilderTDB websiteBuilderTDB;
    private ReqTDB reqTDB;

    private String id = null;
    private String name = null;
    private ProjectTDB website = null;

    @Autowired
    public ComponentBuilderTDB(ProjectBuilderTDB websiteBuilderTDB , ComponentService componentService, ReqTDB reqTDB){
        this.websiteBuilderTDB = websiteBuilderTDB;
        this.componentService = componentService;
        this.reqTDB = reqTDB;
    }

    private void reset(){
        this.id = null;
        this.name = null;
        this.website = null;
    }

    public ComponentBuilderTDB withName( String name ){
        this.name = name;
        return this;
    }

    public ComponentBuilderTDB withWebsite( ProjectTDB website ){
        this.website = website;
        return this;
    }

    public ComponentTDB build(){
        if ( this.website == null )
            this.website = this.websiteBuilderTDB.build();
        ReqCreateComponentDTO dto = new ReqCreateComponentDTO("Mon composant", website.getId());
        ResponseEntity<String> response = reqTDB
                .withAuth(this.website.getAccount().getCookie())
                .withDto(dto)
                .send("/api/v1/component/create");
        ComponentTDB tdb = new ComponentTDB();
        tdb.setWebsite(this.website);
        tdb.setName(this.name);
        tdb.setId(response.getBody());
        reset();
        return tdb;
    }

}
