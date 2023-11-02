package dev.bernouy.cms.feature.website.paramBuilder.dto.response;

import org.springframework.validation.ObjectError;

import java.util.HashMap;

public class ParamBuilderFormatting {

    private String id;
    private Object value;

    private String label;
    private String type;
    private HashMap<String, Object> options;

    public ParamBuilderFormatting(String id, Object value, String label, String type, HashMap<String, Object> options) {
        this.id = id;
        this.value = value;
        this.label = label;
        this.type = type;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, Object> getOptions() {
        return options;
    }

    public void setOptions(HashMap<String, Object> options) {
        this.options = options;
    }
}
