package dev.bernouy.cms.tdb.website;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bernouy.cms.conf.MethodEnum;
import dev.bernouy.cms.conf.TDBMother;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.library.dto.ReqCreateLibrary;
import dev.bernouy.cms.feature.website.page.Page;
import dev.bernouy.cms.feature.website.page.dto.*;
import dev.bernouy.cms.feature.website.project.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PageTDB extends TDBMother {

    private String id;
    private ProjectTDB project;
    private String name;
    private String title;
    private String url;
    private String description;
    private boolean isPublished;
    private LayoutTDB layout;

    public PageTDB build() {
        if ( !isBuild ) {
            isBuild = true;
        }
        if ( project == null ) this.project = new ProjectTDB().build();
        ReqCreatePage dto = new ReqCreatePage(this.project.getId(), "undefined", "url");
        ResponseEntity<String> res = reqTDB.withAuth(this.project.getAccount().getCookie()).withDto(dto).send("page/create");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.id = res.getBody();
        this.name = "undefined";
        this.url = "url";
        return this;
    }

    public PageTDB delete() {
        ResponseEntity<String> res = reqTDB.withAuth(this.project.getAccount().getCookie())
                .send("page/" + this.getId() + "/delete");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.id = null;
        return this;
    }

    public PageTDB setName(String name) {
        ReqSetNamePage dto = new ReqSetNamePage(name);
        ResponseEntity<String> res = reqTDB.withAuth(this.project.getAccount().getCookie()).withDto(dto).send("page/" + this.id + "/setName");
        if (res.getStatusCode() != HttpStatus.CREATED) return this;
        this.name = name;
        return this;
    }


    public PageTDB setPublished(boolean published) {
        ReqSetDeployPage dto = new ReqSetDeployPage(published);
        ResponseEntity<String> res = reqTDB.withAuth(this.project.getAccount().getCookie()).withDto(dto).send("page/" + this.id + "/setDeploy");
        if (res.getStatusCode() != HttpStatus.CREATED) return this;
        this.isPublished = published;
        return this;
    }

    public PageTDB setUrl(String url) {
        ReqSetUrlPage dto = new ReqSetUrlPage(url);
        ResponseEntity<String> res = reqTDB.withAuth(this.project.getAccount().getCookie()).withDto(dto).send("page/" + this.id + "/setUrl");
        if (res.getStatusCode() != HttpStatus.CREATED) return this;
        this.url = url;
        return this;
    }

    public PageTDB setTitle(String title) {
        ReqSetTitlePage dto = new ReqSetTitlePage(title);
        ResponseEntity<String> res = reqTDB.withAuth(this.project.getAccount().getCookie()).withDto(dto).send("page/" + this.id + "/setTitle");
        if (res.getStatusCode() != HttpStatus.CREATED) return this;
        this.title = title;
        return this;
    }

    public PageTDB setDescription(String description) {
        ReqSetDescriptionPage dto = new ReqSetDescriptionPage(description);
        ResponseEntity<String> res = reqTDB.withAuth(this.project.getAccount().getCookie()).withDto(dto).send("page/" + this.id + "/setDescription");
        if (res.getStatusCode() != HttpStatus.CREATED) return this;
        this.description = description;
        return this;
    }

    public PageTDB setLayout(String layoutId) {
        ReqSetLayoutPage dto = new ReqSetLayoutPage(layoutId);
        ResponseEntity<String> res = reqTDB.withAuth(this.project.getAccount().getCookie()).withDto(dto).send("page/" + this.id + "/setLayout");
        return this;
    }

    public String getId() {
        return id;
    }
    public ProjectTDB getProject() {
        return project;
    }
    public String getName() {
        return name;
    }
    public String getTitle() {
        return title;
    }
    public String getUrl() {
        return url;
    }
    public String getDescription() {
        return description;
    }
    public boolean isPublished() {
        return isPublished;
    }
    public String getLayoutId() {
        ResponseEntity<String> res = reqTDB.withMethod(MethodEnum.GET).withAuth(this.project.getAccount().getCookie())
                .send("page/" + this.getId() + "/get");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(res.getBody());
        } catch (Exception e) {return null;}

        return jsonNode.get("layout").get("id").textValue();
    }

    public PageTDB withProject(ProjectTDB project) {
        this.project = project;
        return this;
    }

    public PageTDB withName(String name) {
        this.name = name;
        return this;
    }

    public PageTDB withTitle(String title) {
        this.title = title;
        return this;
    }

    public PageTDB withUrl(String url) {
        this.url = url;
        return this;
    }

    public PageTDB withDescription(String description) {
        this.description = description;
        return this;
    }

    public PageTDB withPublished(boolean published) {
        this.isPublished = published;
        return this;
    }

    public PageTDB withLayout(LayoutTDB layout) {
        this.layout = layout;
        return this;
    }
}
