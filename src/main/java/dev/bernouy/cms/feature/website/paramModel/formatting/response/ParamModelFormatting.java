package dev.bernouy.cms.feature.website.paramModel.formatting.response;

public class ParamModelFormatting {

    private String id;
    private String name;
    private String key;
    private String type;
    private int position;
    private String value;

    public ParamModelFormatting(String id, String name, String key, String type, int position, String value) {
        this.id = id;
        this.name = name;
        this.key = key;
        this.type = type;
        this.position = position;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
