package dev.bernouy.cms.tdb.pojo;

public class ProjectTDB {

    private String     id = null;
    private String     name = null;
    private AccountTDB account = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountTDB getAccount() {
        return account;
    }

    public void setAccount(AccountTDB account) {
        this.account = account;
    }
}
