package dev.bernouy.cms.tdb;

import dev.bernouy.cms.conf.TDBMother;
import dev.bernouy.cms.feature.account.dto.request.RegisterConDTO;
import dev.bernouy.cms.feature.website.component.dto.ReqCreateComponentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ComponentTDB extends TDBMother {

    private static int count = 0;

    private String id = null;
    private String name = "component" + count;
    private ProjectTDB project = null;


    public ComponentTDB build(){
        if(!isBuild){
            isBuild = true;
            count++;
        }
        if ( project == null ) project = new ProjectTDB().build();
        ReqCreateComponentDTO dto = new ReqCreateComponentDTO(name, project.getId());
        ResponseEntity<String> res = reqTDB.withAuth(getAccount().getCookie()).withDto(dto).send("component/create");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.id = res.getBody();
        return this;
    }

    public ComponentTDB withName(String name) {
        if ( isBuild ) return this;
        this.name = name;
        return this;
    }

    public ComponentTDB withProject(ProjectTDB project) {
        if ( isBuild ) return this;
        this.project = project;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectTDB getProject() {
        return project;
    }

    public AccountTDB getAccount() {
        return project.getAccount();
    }

    public String getId() {
        return id;
    }
}
