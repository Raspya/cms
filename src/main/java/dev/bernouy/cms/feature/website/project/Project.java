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
    private String distributionId;

    public String getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(String distributionId) {
        this.distributionId = distributionId;
    }

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
        return "Project{" +
                "name='" + name + '\'' +
                ", domain='" + domain + '\'' +
                ", owner=" + owner +
                ", distributionId='" + distributionId + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project project)) return false;
        return Objects.equals(name, project.name) && Objects.equals(domain, project.domain) && Objects.equals(owner, project.owner) && Objects.equals(distributionId, project.distributionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, domain, owner, distributionId);
    }
}
