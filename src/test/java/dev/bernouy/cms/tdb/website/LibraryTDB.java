package dev.bernouy.cms.tdb.website;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bernouy.cms.conf.MethodEnum;
import dev.bernouy.cms.conf.TDBMother;
import dev.bernouy.cms.feature.website.library.dto.ReqAddRemoveVersionLibrary;
import dev.bernouy.cms.feature.website.library.dto.ReqCreateLibrary;
import dev.bernouy.cms.feature.website.library.dto.ReqNameLibrary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public class LibraryTDB extends TDBMother {

    private String id;
    private String name;
    private ProjectTDB project;
    private ArrayList<VersionTDB> lstVersion;
    public LibraryTDB build() {
        if ( !isBuild ) {
            isBuild = true;
        }
        if ( project == null ) this.project = new ProjectTDB().build();
        ReqCreateLibrary dto = new ReqCreateLibrary(this.project.getId());
        ResponseEntity<String> res = reqTDB.withAuth(this.project.getAccount().getCookie()).withDto(dto).send("library/create");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.id = res.getBody();
        this.name = "undefined";
        return this;
    }

    public LibraryTDB add(String versionId) {
        ReqAddRemoveVersionLibrary dto = new ReqAddRemoveVersionLibrary(versionId);
        ResponseEntity<String> res = reqTDB.withAuth(this.project.getAccount().getCookie()).withDto(dto).send("library/" + this.id + "/add");
        return this;
    }

    public LibraryTDB remove(String versionId) {
        ReqAddRemoveVersionLibrary dto = new ReqAddRemoveVersionLibrary(versionId);
        ResponseEntity<String> res = reqTDB.withAuth(this.project.getAccount().getCookie()).withDto(dto).send("library/" + this.id + "/remove");
        return this;
    }

    public JsonNode list() {
        ResponseEntity<String> res = reqTDB.withMethod(MethodEnum.GET).withAuth(project.getAccount().getCookie())
                .send("library/" + this.id + "/list");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(res.getBody());
        } catch (Exception e) {return null;}
        return jsonNode;
    }


    public LibraryTDB setName(String name) {
        ReqNameLibrary dto = new ReqNameLibrary(name);
        ResponseEntity<String> res = reqTDB.withAuth(this.project.getAccount().getCookie()).withDto(dto).send("library/" + this.id + "/setName");
        if (res.getStatusCode() != HttpStatus.CREATED) return this;
        this.name = name;
        return this;
    }

    public LibraryTDB withProject(ProjectTDB project) {
        this.project = project;
        return this;
    }

    public LibraryTDB withName(String name) {
        this.name = name;
        return this;
    }

    public LibraryTDB withLstVersion(ArrayList<VersionTDB> lstVersion) {
        this.lstVersion = lstVersion;
        return this;
    }


    public String getName() {
        return name;
    }

    public ProjectTDB getProject() {
        return project;
    }

    public String getId() {
        return id;
    }
}
