package dev.bernouy.cms.tdb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bernouy.cms.conf.MethodEnum;
import dev.bernouy.cms.conf.TDBMother;
import dev.bernouy.cms.feature.website.paramModel.dto.*;
import dev.bernouy.cms.tdb.website.VersionTDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ParamModelTDB extends TDBMother {

    private String id;
    private String name;
    private String key;
    private Object value;
    private String type = "string";
    private ParamModelTDB parent;
    private int position;

    private VersionTDB versionTDB = null;

    public ParamModelTDB build() {

        if (this.versionTDB == null) this.versionTDB = new VersionTDB().build();

        ReqCreateParamModel dto = null;
        if (this.parent == null) dto = new ReqCreateParamModel(versionTDB.getId(), this.type);
        else  dto = new ReqCreateParamModel(versionTDB.getId(), this.type, this.parent.getId());

        ResponseEntity<String> res = reqTDB.withAuth(versionTDB.getComponent().getAccount().getCookie())
                                           .withDto(dto).send("component/param/create");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.id = res.getBody();
        this.position = getPosition();
        return this;
    }

    public ParamModelTDB delete() {
        ResponseEntity<String> res = reqTDB.withAuth(versionTDB.getComponent().getAccount().getCookie())
                .send("component/param/" + this.getId() + "/delete");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.id = null;
        return this;
    }

    // With pour avant le build
    // Setters pour après le build

    public ParamModelTDB setName(String name){
        ReqNameParamModel dto = new ReqNameParamModel(name);
        ResponseEntity<String> res = reqTDB.withAuth(versionTDB.getComponent().getAccount().getCookie())
                .withDto(dto).send("component/param/" + this.getId() + "/setName");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.name = name;
        return this;

    }

    public ParamModelTDB setKey(String key){
        ReqKeyParamModel dto = new ReqKeyParamModel(key);
        ResponseEntity<String> res = reqTDB.withAuth(versionTDB.getComponent().getAccount().getCookie())
                .withDto(dto).send("component/param/" + this.getId() + "/setKey");
        System.out.println(res.getStatusCode());
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.key = key;
        return this;
    }

    public ParamModelTDB setPosition(int position) {
        ReqPositionParamModel dto = new ReqPositionParamModel(position);
        ResponseEntity<String> res = reqTDB.withAuth(versionTDB.getComponent().getAccount().getCookie())
                .withDto(dto).send("component/param/" + this.getId() + "/setPosition");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.position = position;
        return this;
    }

    public ParamModelTDB setOption(String key, String option) {
        ReqOptionParamModel dto = new ReqOptionParamModel(key, option);
        ResponseEntity<String> res = reqTDB.withAuth(versionTDB.getComponent().getAccount().getCookie())
                .withDto(dto).send("component/param/" + this.getId() + "/setOption");
        return this;
    }

    public ParamModelTDB setValue(String value){
        ReqValueParamModel dto = new ReqValueParamModel(value);
        ResponseEntity<String> res = reqTDB.withAuth(versionTDB.getComponent().getAccount().getCookie())
                .withDto(dto).send("component/param/" + this.getId() + "/setValue");
        if ( res.getStatusCode() != HttpStatus.CREATED) return this;
        this.value = value;
        return this;
    }

    public ParamModelTDB resetOption(String key){
        ReqKeyParamModel dto = new ReqKeyParamModel(key);
        ResponseEntity<String> res = reqTDB.withAuth(versionTDB.getComponent().getAccount().getCookie())
                .withDto(dto).send("component/param/" + this.getId() + "/resetOption");
        return this;
    }

    public ParamModelTDB resetOptions(){
        ResponseEntity<String> res = reqTDB.withAuth(versionTDB.getComponent().getAccount().getCookie())
                                           .send("component/param/" + this.getId() + "/resetOptions");
        return this;
    }

    public ParamModelTDB withVersion(VersionTDB versionTDB) {
        this.versionTDB = versionTDB;
        return this;
    }
    public ParamModelTDB withName(String name) {
        this.name = name;
        return this;
    }
    public ParamModelTDB withKey(String key) {
        this.key = key;
        return this;
    }
    public ParamModelTDB withValue(Object value) {
        this.value = value;
        return this;
    }
    public ParamModelTDB withType(String type) {
        this.type = type;
        return this;
    }
    public ParamModelTDB withParent(ParamModelTDB parent) {
        this.parent = parent;
        return this;
    }
    public ParamModelTDB withPosition(int position) {
        this.position = position;
        return this;
    }

    public ParamModelTDB getParent() {
        return parent;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        ResponseEntity<String> res = reqTDB.withMethod(MethodEnum.GET).withAuth(versionTDB.getComponent().getAccount().getCookie())
                .send("component/param/" + this.getId() + "/get");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(res.getBody());
        } catch (Exception e) {return null;}

        return jsonNode.get("value").textValue();
    }

    public String getType() {
        return type;
    }

    public JsonNode getOption(String key) {
        ResponseEntity<String> res = reqTDB.withMethod(MethodEnum.GET).withAuth(versionTDB.getComponent().getAccount().getCookie())
                .send("component/param/" + this.getId() + "/get");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(res.getBody());
        } catch (Exception e) {return null;}

        return jsonNode.get(key);
    }

    public Integer getPosition() {
        ResponseEntity<String> res = reqTDB.withMethod(MethodEnum.GET).withAuth(versionTDB.getComponent().getAccount().getCookie())
                .send("component/param/" + this.getId() + "/get");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(res.getBody());
        }catch (Exception e) {return null;}

        return jsonNode.get("position").intValue();
    }

    public VersionTDB getVersion() {
        return versionTDB;
    }
}
