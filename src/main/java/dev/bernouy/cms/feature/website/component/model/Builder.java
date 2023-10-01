package dev.bernouy.cms.feature.website.component.model;

import dev.bernouy.cms.common.AbstractDocument;
import dev.bernouy.cms.feature.website.layout.Layout;
import dev.bernouy.cms.feature.website.page.Page;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Objects;

@CompoundIndexes({
        @CompoundIndex(name = "layoutIndex", def = "{'layout' : 1, 'position': 1}", unique = true),
        @CompoundIndex(name = "pageIndex", def = "{ 'page': 1, 'position': 1 }", unique = true),
})
public class Builder extends AbstractDocument {

    @DBRef
    private Version componentVersion;
    @DBRef
    private Layout layout;
    @DBRef
    private Page page;
    private Integer position;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Version getComponentVersion() {
        return componentVersion;
    }

    public void setComponentVersion(Version componentVersion) {
        this.componentVersion = componentVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Builder builder = (Builder) o;
        return Objects.equals(componentVersion, builder.componentVersion) && Objects.equals(layout, builder.layout) && Objects.equals(page, builder.page) && Objects.equals(position, builder.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(componentVersion, layout, page, position);
    }

    @Override
    public String toString() {
        return "Builder{" +
                "componentVersion=" + componentVersion +
                ", layout=" + layout +
                ", page=" + page +
                ", position=" + position +
                '}';
    }
}
