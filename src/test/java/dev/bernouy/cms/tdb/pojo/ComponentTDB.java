package dev.bernouy.cms.tdb.pojo;

public class ComponentTDB {

    private String id = null;
    private String name = null;
    private ProjectTDB website = null;

    public ComponentTDB() {
    }

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

    public ProjectTDB getWebsite() {
        return website;
    }

    public void setWebsite(ProjectTDB website) {
        this.website = website;
    }
}
