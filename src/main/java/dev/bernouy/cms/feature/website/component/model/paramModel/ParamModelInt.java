package dev.bernouy.cms.feature.website.component.model.paramModel;

public class ParamModelInt extends ParamModel {

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
    public boolean check( String value ){
        int i;
        try{
            i = Integer.parseInt(value);
        } catch ( Exception e ) { return false; }

        return i >= min && i <= max;
    }

    @Override
    public void resetOptions() {
        min = max = null;
    }

}
