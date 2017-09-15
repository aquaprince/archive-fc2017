package com.visa.ncg.canteen;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.ArrayList;

public class AccountRepositoryTest {

    @Test
    public void findAllShouldReturn2Accounts() {
        List<Account> accounts = new ArrayList<>();

        Account a1 = new Account();
        a1.setId(1L);
        Account a2 = new Account();
        a2.setId(2L);
        accounts.add(a1);
        accounts.add(a2);

        InMemAccountRepository repo = new InMemAccountRepository(accounts);
        assertThat(repo.findAll())
                .hasSize(2);
    }

    @Test
    public void saveOneAccountShouldReturn1Account() {
        InMemAccountRepository repo = new InMemAccountRepository();
        Account account = new Account();
        repo.save(account);

        assertThat(repo.findAll())
                .hasSize(1);
    }

    @Test
    public void saveAccountNoIdShouldCreateId() {
        InMemAccountRepository repo = new InMemAccountRepository();
        Account account = new Account();

        assertThat(repo.save(account).getId())
                .isNotNull();
    }

    @Test
    public void findOneWithValidIdShouldReturnAccount() {
        List<Account> accounts = new ArrayList<>();
        Account a1 = new Account();
        a1.setId(1L);
        accounts.add(a1);
        InMemAccountRepository repo = new InMemAccountRepository(accounts);
        assertThat(repo.findOne(1L).getId())
            .isEqualTo(a1.getId());
    }

    @Test
    public void findOneWithInvalidIdShouldReturnNull() {
        List<Account> accounts = new ArrayList<>();
        Account a1 = new Account();
        a1.setId(1L);
        accounts.add(a1);
        InMemAccountRepository repo = new InMemAccountRepository(accounts);
        assertThat(repo.findOne(2L))
                .isNull();
    }

    @Test
    public void findAllWithEmptyRepoShouldReturnEmptyList() {
        InMemAccountRepository repo = new InMemAccountRepository();

        assertThat(repo.findAll())
                .hasSize(0);
    }

    @Test
    public void saveGivesAnUniqueID() {
        InMemAccountRepository repo = new InMemAccountRepository();
        Account a3 = new Account();
        Account a4 = new Account();
        Long a3id = repo.save(a3).getId();
        Long a4id = repo.save(a4).getId();

        assertThat(a3id)
                .isNotEqualTo(a4id);

    }

}
