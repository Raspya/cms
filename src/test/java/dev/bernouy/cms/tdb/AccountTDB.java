package dev.bernouy.cms.tdb;

import dev.bernouy.cms.conf.TDBMother;
import dev.bernouy.cms.feature.account.dto.request.RegisterConDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AccountTDB extends TDBMother {

    private static int idCount  = 0;

    private String email    = "test" + idCount + "@test.com";
    private String password = "Test76930!";
    private String cookie   = null;
    private String id       = null;

    public AccountTDB build(){
        if(!isBuild){
            idCount++;
            isBuild = true;
        }
        RegisterConDTO dto = new RegisterConDTO(email, password);
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

}
