package com.cyecize.demo.api.account;

import com.cyecize.demo.config.db.DataSourceType;
import com.cyecize.demo.config.routing.WithDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@WithDatabase(DataSourceType.TERTIARY)
@Repository("accountRepositoryNonConfig")
public interface AccountRepository extends JpaRepository<Account, Long> {

}
