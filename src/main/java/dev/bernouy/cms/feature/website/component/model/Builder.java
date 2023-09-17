package dev.bernouy.cms.feature.website.component.model;

import dev.bernouy.cms.common.AbstractDocument;

import java.util.Objects;

public class Builder extends AbstractDocument {

    private Version componentVersion;

    public Version getComponentVersion() {
        return componentVersion;
    }

    public void setComponentVersion(Version componentVersion) {
        this.componentVersion = componentVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Builder that = (Builder) o;
        return Objects.equals(componentVersion, that.componentVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(componentVersion);
    }

    @Override
    public String toString() {
        return "ComponentBuilder{" +
                "componentVersion=" + componentVersion +
                '}';
    }
}
