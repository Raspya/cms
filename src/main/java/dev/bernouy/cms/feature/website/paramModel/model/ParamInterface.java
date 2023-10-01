package dev.bernouy.cms.feature.website.paramModel.model;

public interface ParamInterface {
    void updateOption( String key, String value );
    boolean check( String value );
    void resetOptions();

    void setValue(Object value);
}
