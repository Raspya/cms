package dev.bernouy.cms.feature.website.paramModel.model;

import java.util.HashMap;

public class ParamModelList extends ParamModel  {

    private Integer min;
    private Integer max;


    public ParamModelList(Integer min, Integer max) {
        this.min = min;
        this.max = max;
        this.value = "0";
    }

    public ParamModelList() {
        this.min = null;
        this.max = null;
    }

    public HashMap<String, Object> optionsToMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("min", this.min);
        hashMap.put("min", this.max);
        return hashMap;
    }
    @Override
    public void updateOption( String key, String value ){
        int i = Integer.parseInt(value);
        switch ( key ){
            case "min" -> {
                if ( max == null || i < max)
                    this.min = i;
            }
            case "max" -> {
                if ( min == null || i > min)
                    this.max = i;
            }
        }
    }

    @Override
    public boolean check( String value ){
        try{
            Integer.parseInt(value);
        } catch ( Exception e ) { return false; }
        return true;
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

    public Integer getMin() {
        return min;
    }

    public void setValue(String value) {
        if (check(value))
            this.value = value;
    }

    @Override
    public boolean childAvailable() {
        return true;
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
