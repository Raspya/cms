package dev.bernouy.cms.tdb.website;

import dev.bernouy.cms.conf.TDBMother;
import dev.bernouy.cms.feature.website.component.dto.ReqCreateVersion;
import dev.bernouy.cms.tdb.account.AccountTDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class VersionTDB extends TDBMother {

    private String id;
    private boolean isDeploy;
    private int majorVersion;
    private int minorVersion;
    private int patchVersion;
    private String pathCSS;
    private String pathJS;
    private ComponentTDB component;

    public VersionTDB build() {
        if ( !isBuild ) {
            isBuild = true;
        }
        if ( component == null ) this.component = new ComponentTDB().build();
        ReqCreateVersion dto = new ReqCreateVersion("major", component.getId());
        ResponseEntity<String> res = reqTDB.withAuth(getAccount().getCookie()).withDto(dto).send("component/version/create");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.id = res.getBody();
        return this;
    }

    public VersionTDB withComponent(ComponentTDB component) {
        if ( isBuild ) return this;
        this.component = component;
        return this;
    }

    // Setters ou tout autre méthode ex : création d'une nouvelle version, upload

    public String getId() {
        return id;
    }

    public boolean isDeploy() {
        return isDeploy;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getPatchVersion() {
        return patchVersion;
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
