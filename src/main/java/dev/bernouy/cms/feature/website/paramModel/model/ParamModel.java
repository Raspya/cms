package dev.bernouy.cms.feature.website.paramModel.model;

import dev.bernouy.cms.common.AbstractDocument;
import dev.bernouy.cms.feature.website.version.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;

@CompoundIndexes({
        @CompoundIndex(name = "keyIndex", def = "{'parent' : 1, 'key': 1}"),
        @CompoundIndex(name = "searchIndex", def = "{ 'parent': 1, 'componentVersion': 1 }")
})
public abstract class ParamModel extends AbstractDocument implements ParamInterface {

    @DBRef
    protected Version componentVersion;
    @DBRef
    protected ParamModel parent;
    protected String name;
    protected String key;
    protected String type;
    protected int position;
    protected Object value;

    public int getPosition() { return position;}
    public void setPosition(int position) {
        this.position = position;
    }

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

    public Object getValue() {
        return value;
    }

    public boolean childAvailable() {
        return false;
    }

}
