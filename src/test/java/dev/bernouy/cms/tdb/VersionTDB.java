package dev.bernouy.cms.tdb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bernouy.cms.conf.MethodEnum;
import dev.bernouy.cms.conf.TDBMother;
import dev.bernouy.cms.feature.website.component.dto.ReqCreateVersion;
import dev.bernouy.cms.feature.website.component.dto.ReqUploadFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class VersionTDB extends TDBMother {

    private String id;
    private boolean isDeploy;
    private int majorVersion;
    private int minorVersion;
    private int patchVersion;
    private String typeVersion = "";
    private String pathCSS;
    private String pathJS;
    private ComponentTDB component;

    public VersionTDB build() {
        if ( !isBuild ) {
            isBuild = true;
        }
        if ( component == null ) this.component = new ComponentTDB().build();
        ReqCreateVersion dto = new ReqCreateVersion(this.typeVersion, component.getId());
        ResponseEntity<String> res = reqTDB.withAuth(getAccount().getCookie()).withDto(dto).send("component/version/create");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.id = res.getBody();
        return this;
    }

    // Setters ou tout autre méthode ex : création d'une nouvelle version, upload

    public VersionTDB setUploadJS(String pathJS){
        ReqUploadFile dto = new ReqUploadFile(pathJS);
        ResponseEntity<String> res = reqTDB.withAuth(getAccount().getCookie()).withDto(dto).send("component/version/" + this.getId() + "/uploadJS");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.pathJS = pathJS;
        return this;
    }

    public VersionTDB setUploadCSS(String pathCSS){
        ReqUploadFile dto = new ReqUploadFile(pathCSS);
        ResponseEntity<String> res = reqTDB.withAuth(getAccount().getCookie()).withDto(dto).send("component/version/" + this.getId() + "/uploadCSS");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.pathCSS = pathCSS;
        return this;
    }

    public VersionTDB setDeploy(){
        ResponseEntity<String> res = reqTDB.withAuth(getAccount().getCookie()).send("component/version/" + this.getId() + "/deploy");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.isDeploy = true;
        return this;
    }

    public VersionTDB withTypeVersion(String typeVersion) {
        if ( isBuild ) return this;
        this.typeVersion = typeVersion;
        return this;
    }
    public VersionTDB withComponent(ComponentTDB component) {
        if ( isBuild ) return this;
        this.component = component;
        return this;
    }

    public VersionTDB withMajorVersion(int majorVersion) {
        if ( isBuild ) return this;
        this.majorVersion = majorVersion;
        return this;
    }

    public VersionTDB withMinorVersion(int minorVersion) {
        if ( isBuild ) return this;
        this.minorVersion = minorVersion;
        return this;
    }

    public VersionTDB withPatchVersion(int patchVersion) {
        if ( isBuild ) return this;
        this.patchVersion = patchVersion;
        return this;
    }

    public VersionTDB withPathJS(String pathJS) {
        if ( isBuild ) return this;
        this.pathJS = pathJS;
        return this;
    }

    public VersionTDB withPathCSS(String pathCSS) {
        if ( isBuild ) return this;
        this.pathCSS = pathCSS;
        return this;
    }

    public VersionTDB withDeploy(boolean deploy) {
        if ( isBuild ) return this;
        this.isDeploy = deploy;
        return this;
    }

    public String getId() {
        return id;
    }

    public boolean isDeploy() {
        return isDeploy;
    }

    public Integer getMajorVersion() {
        ResponseEntity<String> res = reqTDB.withMethod(MethodEnum.GET).withAuth(getAccount().getCookie())
                .send("component/version/" + this.getId() + "/get");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(res.getBody());
        }catch (Exception e) {return null;}

        return jsonNode.get("majorVersion").intValue();
    }

    public Integer getMinorVersion() {
        ResponseEntity<String> res = reqTDB.withMethod(MethodEnum.GET).withAuth(getAccount().getCookie())
                .send("component/version/" + this.getId() + "/get");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(res.getBody());
        }catch (Exception e) {return null;}

        return jsonNode.get("minorVersion").intValue();
    }

    public Integer getPatchVersion() {
        ResponseEntity<String> res = reqTDB.withMethod(MethodEnum.GET).withAuth(getAccount().getCookie())
                .send("component/version/" + this.getId() + "/get");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(res.getBody());
        }catch (Exception e) {return null;}

        return jsonNode.get("patchVersion").intValue();
    }

    public String getPathCSS() {
        return pathCSS;
    }

    public String getPathJS() {
        return pathJS;
    }

    public ComponentTDB getComponent() {
        return component;
    }

    public ProjectTDB getProject() {
        return component.getProject();
    }

    public AccountTDB getAccount() {
        return component.getAccount();
    }


}
