package dev.bernouy.cms.feature.website.library;

import dev.bernouy.cms.common.AbstractDocument;

public class Library extends AbstractDocument {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
