package dev.bernouy.cms.feature.website.project;

import dev.bernouy.cms.feature.account.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, String> {

    List<Project> getProjectsByOwner(Account owner );
    boolean existsByDomain(String domain);

}
