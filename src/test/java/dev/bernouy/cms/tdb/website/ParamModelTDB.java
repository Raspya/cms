package dev.bernouy.cms.tdb.website;

import dev.bernouy.cms.conf.TDBMother;

public class ParamModelTDB extends TDBMother {

    private String id;
    private String name;
    private String key;
    private Object value;
    private String type;
    private int position;

    public ParamModelTDB build() {
        return this;
    }

    // With pour avant le build
    // Setters pour après le build

    public ParamModelTDB setName(String name){
        this.name = name;
        return this;
    }

    public ParamModelTDB setKey(String key){
        this.key = key;
        return this;
    }

    public ParamModelTDB setValue(String key, Object value){
        this.value = value;
        return this;
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
        return value;
    }

    public String getType() {
        return type;
    }

    public int getPosition() {
        // On fera la requête ici
        // On transforme en JsonNode
        // On récupère la position
        return -1;
    }
}
