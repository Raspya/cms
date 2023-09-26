package dev.bernouy.cms.feature.website.component.model.ParamModel;

public class ParamModelString extends ParamModel{

    @Override
    public void updateOption( String key, Object value ){
        if ( key.equals("min") || key.equals("max") )
            this.options.put(key, value);
    }

    @Override
    public void setValue(Object value) {
        if ( !check(value) ) return;
        this.value = value;
    }

    @Override
    public boolean check(Object value ){
        int i;
        try{
            i = Integer.parseInt(this.getOptions().get("min").toString());
        } catch ( Exception e ){ return false; }
        // Il faut cast à chaque fois l'object dans un try catch
        // Que ce soit list, integer, double, ...
        // Il faut cast aussi value en fonction du paramètre
        if ( i > value.toString().length() && i < value.toString().length() )
            return false;
        return true;
    }

}
