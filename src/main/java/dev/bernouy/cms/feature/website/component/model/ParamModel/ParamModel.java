package dev.bernouy.cms.feature.website.component.model.ParamModel;

import dev.bernouy.cms.common.AbstractDocument;
import dev.bernouy.cms.feature.website.component.model.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashMap;

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
    protected Object value;

    protected HashMap<String, Object> options;

    public HashMap<String, Object> getOptions() {
        return options;
    }

    public void removeOption(String key) {
        if (options.containsKey(key)) options.remove(key);
    }

    public void setOptions(HashMap<String, Object> options) {
        this.options = options;
    }

    public Object getValue() {
        return value;
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

}
