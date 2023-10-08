package dev.bernouy.cms.tdb.website;

import dev.bernouy.cms.conf.TDBMother;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.layout.dto.ReqCreateLayout;
import dev.bernouy.cms.feature.website.layout.dto.ReqSetDefaultLayout;
import dev.bernouy.cms.feature.website.page.dto.ReqSetNamePage;
import dev.bernouy.cms.tdb.ParamModelTDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LayoutTDB extends TDBMother {

    private String id;
    private String name = "undefined";
    private boolean isDefault;
    private ProjectTDB project;

    public LayoutTDB build() {
        if ( !isBuild ) {
            isBuild = true;
        }
        if ( project == null ) this.project = new ProjectTDB().build();
        ReqCreateLayout dto = new ReqCreateLayout(this.project.getId(), this.name);
        ResponseEntity<String> res = reqTDB.withAuth(this.project.getAccount().getCookie()).withDto(dto).send("layout/create");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.id = res.getBody();
        return this;
    }

    public LayoutTDB delete() {
        ResponseEntity<String> res = reqTDB.withAuth(this.project.getAccount().getCookie())
                .send("layout/" + this.getId() + "/delete");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.id = null;
        return this;
    }

    public LayoutTDB setName(String name) {
        ReqSetNamePage dto = new ReqSetNamePage(name);
        ResponseEntity<String> res = reqTDB.withAuth(this.project.getAccount().getCookie()).withDto(dto).send("layout/" + this.id + "/setName");
        if (res.getStatusCode() != HttpStatus.CREATED) return this;
        this.name = name;
        return this;
    }

    public LayoutTDB setDefault(boolean aDefault) {
        ReqSetDefaultLayout dto = new ReqSetDefaultLayout(aDefault);
        ResponseEntity<String> res = reqTDB.withAuth(this.project.getAccount().getCookie()).withDto(dto).send("layout/" + this.id + "/setDefault");
        if (res.getStatusCode() != HttpStatus.CREATED) return this;
        this.isDefault = aDefault;
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public ProjectTDB getProject() {
        return project;
    }

    public LayoutTDB withProject(ProjectTDB project) {
        this.project = project;
        return this;
    }

    public LayoutTDB withName(String name) {
        this.name = name;
        return this;
    }

    public LayoutTDB withDefault(boolean isDefault) {
        this.isDefault = isDefault;
        return this;
    }
}
