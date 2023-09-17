package dev.bernouy.cms.feature.website.component.model;

import dev.bernouy.cms.common.AbstractDocument;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Objects;

@CompoundIndexes({
        @CompoundIndex(name = "keyIndex", def = "{'parent' : 1, 'key': 1}"),
        @CompoundIndex(name = "searchIndex", def = "{ 'parent': 1, 'componentVersion': 1 }")
})
public class ParamModel extends AbstractDocument {

    @DBRef
    private Version componentVersion;
    @DBRef
    private ParamModel parent;
    private String name;
    private String key;
    private String type;

    public Version getComponentVersion() {
        return componentVersion;
    }

    public ParamModel getParent() {
        return parent;
    }

    public void setParent(ParamModel parent) {
        this.parent = parent;
    }

    public void setComponentVersion(Version componentVersion) {
        this.componentVersion = componentVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParamModel that = (ParamModel) o;
        return Objects.equals(componentVersion, that.componentVersion) && Objects.equals(parent, that.parent) && Objects.equals(name, that.name) && Objects.equals(key, that.key) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(componentVersion, parent, name, key, type);
    }

    @Override
    public String toString() {
        return "ParamModel{" +
                "componentVersion=" + componentVersion +
                ", parent=" + parent +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
