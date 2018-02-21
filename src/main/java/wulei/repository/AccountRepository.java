package wulei.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import wulei.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long>,
        QueryByExampleExecutor<Account> {
    Account findByUsername(String username);
}
