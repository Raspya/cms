package dev.bernouy.cms.tdb.pojo;

public class AccountTDB {

    protected String email    = null;
    protected String password = null;
    protected String cookie   = null;
    protected String id       = null;

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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public void setId(String id) {
        this.id = id;
    }
}