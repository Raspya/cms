package dev.bernouy.cms.feature.website.paramModel.model;

public class ParamModelFloat extends ParamModel  {

    private Integer min;
    private Integer max;


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
        double i;
        try{
            i = Double.parseDouble(value);
        } catch ( Exception e ) { return false; }
        return i >= min && i <= max;
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
