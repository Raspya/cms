package dev.bernouy.cms.tdb.account;

import dev.bernouy.cms.conf.MethodEnum;
import dev.bernouy.cms.conf.TDBMother;
import dev.bernouy.cms.feature.account.dto.request.ReqRegisterConDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AccountTDB extends TDBMother {

    private static int idCount  = 0;

    private String email    = "test" + idCount + "@test.com";
    private String password = "Test76930!";
    private String cookie   = null;
    private String id       = null;

    public AccountTDB build(){
        if ( isBuild ) return this;
        idCount++;
        isBuild = true;
        ReqRegisterConDTO dto = new ReqRegisterConDTO(email, password);
        ResponseEntity<String> res = reqTDB.withDto(dto).send("account/register");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.id = res.getBody();
        res = reqTDB.withDto(dto).send("account/login");
        this.cookie = res.getHeaders().get("Set-Cookie").get(0).split(";")[0].split("=")[1];
        return this;
    }

    // with... pour avant le build

    public AccountTDB withEmail(String email) {
        if ( isBuild ) return this;
        this.email = email;
        return this;
    }

    public AccountTDB withPassword(String password) {
        if ( isBuild ) return this;
        this.password = password;
        return this;
    }

    // Setters pour après le build

    // Setters forcé pour les tests
    public void setForceCookie(String cookie) {
        this.cookie = cookie;
    }

    public void setForceId(String id) {
        this.id = id;
    }

    public void setForceEmail(String email) {
        this.email = email;
    }

    public void setForcePassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCookie() {
        return cookie;
    }

    public String getId() {
        return id;
    }


    // Autres méthodes
    public boolean isValidToken(String token){
        ResponseEntity<String> res = reqTDB.withMethod(MethodEnum.GET).withAuth(token).send("account/isValidToken");
        return res.getStatusCode() == HttpStatus.OK;
    }

    public boolean login(String email, String password){
        ReqRegisterConDTO dto = new ReqRegisterConDTO(email, password);
        ResponseEntity<String> res = reqTDB.withDto(dto).send("account/login");
        return res.getStatusCode() == HttpStatus.OK;

    }

}
