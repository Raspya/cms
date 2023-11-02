package dev.bernouy.cms.tdb.website;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bernouy.cms.conf.MethodEnum;
import dev.bernouy.cms.conf.TDBMother;
import dev.bernouy.cms.feature.website.layout.formatting.request.ReqCreateLayout;
import dev.bernouy.cms.feature.website.layout.formatting.request.ReqSetDefaultLayout;
import dev.bernouy.cms.feature.website.page.dto.request.ReqSetNamePage;
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

    public Boolean isDefault() {
        ResponseEntity<String> res = reqTDB.withMethod(MethodEnum.GET).withAuth(this.project.getAccount().getCookie())
                .send("layout/" + this.getId() + "/get");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(res.getBody());
        }catch (Exception e) {return null;}

        return jsonNode.get("aBoolean").booleanValue();
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
