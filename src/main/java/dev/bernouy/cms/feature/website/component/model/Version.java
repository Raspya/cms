package dev.bernouy.cms.feature.website.component.model;

import dev.bernouy.cms.common.AbstractDocument;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Objects;

public class Version extends AbstractDocument {

    @DBRef
    private Component component;
    private int majorVersion;
    private int minorVersion;
    private int patchVersion;
    private boolean isDeploy;

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
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

    public boolean isDeploy() {
        return isDeploy;
    }

    public void setDeploy(boolean deploy) {
        isDeploy = deploy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version that = (Version) o;
        return majorVersion == that.majorVersion && minorVersion == that.minorVersion && patchVersion == that.patchVersion && isDeploy == that.isDeploy && Objects.equals(component, that.component);
    }

    @Override
    public int hashCode() {
        return Objects.hash(component, majorVersion, minorVersion, patchVersion, isDeploy);
    }

    @Override
    public String toString() {
        return "ComponentVersion{" +
                "component=" + component +
                ", majorVersion=" + majorVersion +
                ", minorVersion=" + minorVersion +
                ", patchVersion=" + patchVersion +
                ", isDeploy=" + isDeploy +
                '}';
    }
}
