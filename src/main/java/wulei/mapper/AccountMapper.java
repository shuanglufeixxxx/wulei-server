package wulei.mapper;

import wulei.domain.Account;

public interface AccountMapper {
    Account selectByUsername(String username);

    int countByUsername(String username);

    int countByUsernamePassword(String username, String password);

    void insert(Account account);
}