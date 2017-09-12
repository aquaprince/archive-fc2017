package com.visa.ncg.canteen;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class AccountRepository {

    private HashMap<Long, Account> accountRepo = new HashMap<Long, Account>();
    private AtomicLong atom = new AtomicLong();
    public AccountRepository(List<Account> accounts) {

        for (Account ac : accounts) {
            accountRepo.put(ac.getId(), ac);
        }

    }

    public AccountRepository() {}

    public Account findOne(Long id) {
        // returns the Account that has the given id,
        // if there's no Account with that id, return null
        return accountRepo.get(id);
    }

    public Account save(Account entity) {
        // if account's id is already set, DON'T replace it
        // if the account came in with NO id, replace it with one generated from AtomicLong.
        // return the Account object that must now have its id set
        Long longval = entity.getId();
        Long toUse = atom.get();
        if (longval == null) {

            while (accountRepo.containsKey(toUse)) {
                toUse = atom.incrementAndGet();
            }
            entity.setId(toUse.longValue());
        }

        accountRepo.put(toUse.longValue(), entity);
        return entity;
    }

    public List<Account> findAll() {
        // return all accounts as a List, or an empty List if there are no accounts
        List<Account> accountL = new ArrayList<Account>(accountRepo.values());
        return accountL;
    }

    }

