package dev.bernouy.cms.feature.website.paramModel.model;

import java.util.HashMap;

public class ParamModelString extends ParamModel {

    private Integer min;
    private Integer max;

    public ParamModelString(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    public ParamModelString() {
        this.min = null;
        this.max = null;
    }
    @Override
    public void updateOption( String key, String value ){
        int i = Integer.parseInt(value);
        switch ( key ){
            case "min" -> {
                if ( max == null || i < max )
                    this.min = i;
            }
            case "max" -> {
                if ( min == null || i > min )
                    this.max = i;
            }
        }
    }

    public HashMap<String, Object> optionsToMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("min", this.min);
        hashMap.put("min", this.max);
        return hashMap;
    }

    @Override
    public boolean check( String value ){
        if ( value.length() >= min && value.length() <= max )
            return true;
        return false;
    }

    @Override
    public void resetOption(String key) {
        switch (key) {
            case "min" -> {min = null;}
            case "max" -> {max = null;}
        }
    }
    @Override
    public void resetOptions() {
        min = max = null;
    }

    public void setValue(String value) {
        if (check(value))
            this.value = value;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
