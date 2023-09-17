package dev.bernouy.cms.feature.website.project;

import dev.bernouy.cms.common.AbstractDocument;
import dev.bernouy.cms.feature.account.Account;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Objects;

public class Project extends AbstractDocument {

    private String  name;

    @Indexed(unique = true)
    private String  domain;
    @DBRef
    private Account owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Website{" +
                "name='" + name + '\'' +
                ", domain='" + domain + '\'' +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project website = (Project) o;
        return Objects.equals(name, website.name) && Objects.equals(domain, website.domain) && Objects.equals(owner, website.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, domain, owner);
    }
}
