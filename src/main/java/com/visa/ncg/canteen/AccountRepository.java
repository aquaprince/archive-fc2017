package com.visa.ncg.canteen;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AccountRepository {

  private Map<Long, Account> accountIdMap = new HashMap<>();

  private final AtomicLong idGenerator = new AtomicLong();

  public AccountRepository() {
  }

  public AccountRepository(List<Account> accounts) {
    for (Account account : accounts) {
      accountIdMap.put(account.getId(), account);
    }
  }

  public List<Account> findAll() {
    return new ArrayList<>(accountIdMap.values());
  }

  public Account findOne(Long id) {
    return accountIdMap.get(id);
  }

  public Account save(Account account) {
    if (account.getId() == null) {
      account.setId(idGenerator.getAndIncrement());
    }
    accountIdMap.put(account.getId(), account);

    return account;
  }
}
