package dev.bernouy.cms.feature.website.component.model.ParamModel;

public interface ParamInterface {

    void setValue( Object value );
    void updateOption( String key, Object value );
    boolean check( Object value );

}
