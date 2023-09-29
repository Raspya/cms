package dev.bernouy.cms.tdb.pojo;

import dev.bernouy.cms.ReqTDB;
import dev.bernouy.cms.feature.website.component.dto.ReqPositionParamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class ParamModelTDB{

    private String id;
    private String type;
    private Object value;
    private String key;
    private int position;
    private String name;
    private ParamModelTDB parent;
    private VersionTDB version;

    public VersionTDB getVersion() {
        return version;
    }

    public void setVersion(VersionTDB version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setValue(Object value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParamModelTDB getParent() {
        return parent;
    }

    public void setParent(ParamModelTDB parent) {
        this.parent = parent;
    }
}
