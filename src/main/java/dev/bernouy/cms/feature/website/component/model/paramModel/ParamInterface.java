package dev.bernouy.cms.feature.website.component.model.paramModel;

public interface ParamInterface {

    void setValue( String value );
    void updateOption( String key, String value );
    boolean check( String value );

}
