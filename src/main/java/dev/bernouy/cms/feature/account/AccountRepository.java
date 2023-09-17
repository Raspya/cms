package dev.bernouy.cms.feature.account;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {

    public boolean existsAccountByEmail ( String email ) ;
    public Account getAccountByEmail ( String email );

}
