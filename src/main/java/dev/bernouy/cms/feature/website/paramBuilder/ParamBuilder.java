package dev.bernouy.cms.feature.website.paramBuilder;

import dev.bernouy.cms.common.AbstractDocument;
import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Objects;

@CompoundIndexes({
        @CompoundIndex(name = "keyIndex", def = "{'parent' : 1, 'key': 1}", unique = true),
        @CompoundIndex(name = "searchIndex", def = "{ 'parent': 1, 'componentBuilder': 1 }")
})
public class ParamBuilder extends AbstractDocument {

    @DBRef
    private Builder componentBuilder;
    @DBRef
    private ParamModel paramModel;
    private Object value;

    public ParamModel getParamModel() {
        return paramModel;
    }

    public void setParamModel(ParamModel paramModel) {
        this.paramModel = paramModel;
    }

    public Builder getComponentBuilder() {
        return componentBuilder;
    }

    public void setComponentBuilder(Builder componentBuilder) {
        this.componentBuilder = componentBuilder;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParamBuilder that = (ParamBuilder) o;
        return  Objects.equals(paramModel, that.paramModel) && Objects.equals(componentBuilder, that.componentBuilder) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(componentBuilder, paramModel, value);
    }

    @Override
    public String toString() {
        return "ParamBuilder{" +
                ", componentBuilder=" + componentBuilder +
                ", paramModel=" + paramModel +
                ", value=" + value +
                '}';
    }
}
