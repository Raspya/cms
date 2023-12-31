package dev.bernouy.cms.feature.website.paramModel.model;

import dev.bernouy.cms.common.AbstractDocument;
import dev.bernouy.cms.feature.website.version.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashMap;

public abstract class ParamModel extends AbstractDocument implements ParamInterface {

    @DBRef
    protected Version componentVersion;
    protected String parent;
    protected String name;
    protected String key;
    protected String type;
    protected int position;
    protected String value;

    public int getPosition() { return position;}
    public void setPosition(int position) {
        this.position = position;
    }

    public Version getComponentVersion() {
        return componentVersion;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
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

    public String getValue() {
        return value;
    }

    public abstract HashMap<String, Object> optionsToMap();

    public boolean childAvailable() {
        return false;
    }

    public String toParamString(){
        return "\"" + this.key + "\":" + "\"" + this.value + "\"";
    }
}
