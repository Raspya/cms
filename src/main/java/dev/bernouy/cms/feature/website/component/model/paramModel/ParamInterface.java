package dev.bernouy.cms.feature.website.component.model.paramModel;

public interface ParamInterface {
    void updateOption( String key, String value );
    boolean check( String value );
    void resetOptions();

}
