package dev.bernouy.cms.tdb.pojo;

public class VersionTDB {

    private String id;
    private String typeVersion;
    private ComponentTDB componentTDB;
    private boolean isDeploy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeVersion() {
        return typeVersion;
    }

    public void setTypeVersion(String typeVersion) {
        this.typeVersion = typeVersion;
    }

    public ComponentTDB getComponentTDB() {
        return componentTDB;
    }

    public void setComponentTDB(ComponentTDB componentTDB) {
        this.componentTDB = componentTDB;
    }

    public boolean isDeploy() {
        return isDeploy;
    }

    public void setDeploy(boolean deploy) {
        isDeploy = deploy;
    }
}
