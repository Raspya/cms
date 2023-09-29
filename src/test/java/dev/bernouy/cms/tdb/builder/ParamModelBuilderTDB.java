package dev.bernouy.cms.tdb.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bernouy.cms.MethodEnum;
import dev.bernouy.cms.ReqTDB;
import dev.bernouy.cms.feature.website.component.dto.ReqCreateParamModel;
import dev.bernouy.cms.feature.website.component.dto.ReqPositionParamModel;
import dev.bernouy.cms.tdb.pojo.ParamModelTDB;
import dev.bernouy.cms.tdb.pojo.VersionTDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ParamModelBuilderTDB{

    @Autowired
    private VersionBuilderTDB versionBuilderTDB;
    @Autowired
    private ReqTDB reqTDB;

    private String type = "String";
    private Object value;
    private String key;
    private int position;
    private String name;
    private ParamModelTDB parent;
    private VersionTDB version;
    protected ObjectMapper objectMapper = new ObjectMapper();

    private void reset(){
        this.type = "String";
        this.value = "";
        this.key = "";
        this.name = "";
        this.parent = null;
        this.version = null;
    }

    public ParamModelBuilderTDB withType(String type){
        this.type = type;
        return this;
    }

    public ParamModelBuilderTDB withValue(Object value){
        this.value = value;
        return this;
    }

    public ParamModelBuilderTDB withKey(String key){
        this.key = key;
        return this;
    }

    public ParamModelBuilderTDB withPosition(int position){
        this.position = position;
        return this;
    }

    public ParamModelBuilderTDB withName(String name){
        this.name = name;
        return this;
    }

    public ParamModelBuilderTDB withParent(ParamModelTDB parent){
        this.parent = parent;
        return this;
    }

    public ParamModelBuilderTDB withVersion(VersionTDB version){
        this.version = version;
        return this;
    }

    public ParamModelTDB build() {
        ParamModelTDB paramModelTDB = new ParamModelTDB();
        if ( this.version == null )
            this.version = this.versionBuilderTDB.build();
        ReqCreateParamModel dto;

        if (this.parent != null) dto = new ReqCreateParamModel(version.getId(), this.type, this.parent.getId());
        else dto = new ReqCreateParamModel(version.getId(), this.type);

        ResponseEntity<String> res = this.reqTDB.withAuth(this.version.getComponentTDB().getWebsite().getAccount().getCookie()).withDto(dto).send("/api/v1/component/param/create");
        if ( res.getStatusCode() == HttpStatus.CREATED )
            paramModelTDB.setId(res.getBody());

        ResponseEntity<String> res2 = this.reqTDB.withAuth(this.version.getComponentTDB().getWebsite().getAccount().getCookie()).withDto(dto).withMethod(MethodEnum.GET).send("/api/v1/component/param/" + paramModelTDB.getId() +  "/get");
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(res2.getBody());
        }catch (Exception e) { return null; }

        paramModelTDB.setType(this.type);
        paramModelTDB.setValue(this.value);
        paramModelTDB.setKey(this.key);
        paramModelTDB.setName(this.name);
        paramModelTDB.setPosition(jsonNode.get("position").intValue());
        paramModelTDB.setParent(this.parent);
        paramModelTDB.setVersion(this.version);

        reset();
        return paramModelTDB;
    }


}
