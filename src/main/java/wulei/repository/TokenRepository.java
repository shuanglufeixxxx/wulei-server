package wulei.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import wulei.domain.Token;

import javax.transaction.Transactional;

public interface TokenRepository extends CrudRepository<Token, String>,
        QueryByExampleExecutor<Token> {

    @Transactional
    @Modifying
    @Query(value = "delete from token where username = ?", nativeQuery = true)
    void deleteByUsername(String username);
}
