package dev.bernouy.cms.feature.website.paramModel.model;

import dev.bernouy.cms.common.BasicException;

import java.util.HashMap;

public class ParamModelLink extends ParamModel  {


    public ParamModelLink() {}


    public HashMap<String, Object> optionsToMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        return hashMap;
    }

    public void setValue(String value) {
        this.value = value;
    }
    @Override
    public void updateOption(String key, String value) {
        throw new BasicException("There are no options in Link type");
    }

    @Override
    public boolean check(String value) {
        return false;
    }

    @Override
    public void resetOptions() {
        throw new BasicException("There are no options in Link type");
    }

    @Override
    public void resetOption(String key) {
        throw new BasicException("there are no options in Link type");
    }




}
