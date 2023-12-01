package dev.bernouy.cms.feature.website.paramBuilder;

import dev.bernouy.cms.common.AbstractDocument;
import dev.bernouy.cms.feature.website.builder.Builder;
import dev.bernouy.cms.feature.website.paramModel.model.ParamModel;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Objects;

public class ParamBuilder extends AbstractDocument {

    @DBRef
    private Builder builder;
    @DBRef
    private ParamModel paramModel;
    private Object value;
    private String parent;

    public ParamModel getParamModel() {
        return paramModel;
    }

    public void setParamModel(ParamModel paramModel) {
        this.paramModel = paramModel;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Builder getBuilder() {
        return builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
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
        return  Objects.equals(paramModel, that.paramModel) && Objects.equals(builder, that.builder) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(builder, paramModel, value);
    }

    @Override
    public String toString() {
        return "ParamBuilder{" +
                ", componentBuilder=" + builder +
                ", paramModel=" + paramModel +
                ", value=" + value +
                '}';
    }
}
