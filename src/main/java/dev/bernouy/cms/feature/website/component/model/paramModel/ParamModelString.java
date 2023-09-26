package dev.bernouy.cms.feature.website.component.model.paramModel;

public class ParamModelString extends ParamModel {

    private Integer min;
    private Integer max;

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

    @Override
    public void setValue(String value) {
        if ( !check(value) ) return;
        this.value = value;
    }

    @Override
    public boolean check( String value ){
        if ( value.length() >= min && value.length() <= max )
            return false;
        return true;
    }

}
