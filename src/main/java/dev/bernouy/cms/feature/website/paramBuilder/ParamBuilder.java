package dev.bernouy.cms.feature.website.paramBuilder;

import dev.bernouy.cms.common.AbstractDocument;
import dev.bernouy.cms.feature.website.builder.Builder;
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
    private ParamBuilder parent;
    @DBRef
    private Builder componentBuilder;
    private String key;
    private Object value;

    public ParamBuilder getParent() {
        return parent;
    }

    public void setParent(ParamBuilder parent) {
        this.parent = parent;
    }

    public Builder getComponentBuilder() {
        return componentBuilder;
    }

    public void setComponentBuilder(Builder componentBuilder) {
        this.componentBuilder = componentBuilder;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
        return Objects.equals(parent, that.parent) && Objects.equals(componentBuilder, that.componentBuilder) && Objects.equals(key, that.key) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, componentBuilder, key, value);
    }

    @Override
    public String toString() {
        return "ParamBuilder{" +
                "parent=" + parent +
                ", componentBuilder=" + componentBuilder +
                ", key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
