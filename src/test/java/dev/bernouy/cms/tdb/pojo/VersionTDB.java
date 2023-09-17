package dev.bernouy.cms.tdb.pojo;

public class VersionTDB {

    private String id;
    private int majorVersion;
    private int minorVersion;
    private int patchVersion;
    private ComponentTDB componentTDB;
    private boolean isDeploy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getPatchVersion() {
        return patchVersion;
    }

    public void setPatchVersion(int patchVersion) {
        this.patchVersion = patchVersion;
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
