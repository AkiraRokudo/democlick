package com.example.democlick.service;

import com.example.democlick.component.AccountLockFactory;
import com.example.democlick.exception.AccountConflictException;
import com.example.democlick.exception.AccountNotFoundException;
import com.example.democlick.model.Account;
import com.example.democlick.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;

/**
 * @author AkiraRokudo on 22.09.2020 in one of sun day
 */
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository repository;

    private AccountLockFactory lockFactory;

    public AccountServiceImpl(AccountRepository repository, AccountLockFactory lockFactory) {
        this.repository = repository;
        this.lockFactory = lockFactory;
    }

    @Override
    @Transactional
    public Account addMoney(Long accountFromId, BigDecimal money) throws AccountNotFoundException {
        Account account = repository.findById(accountFromId).orElseThrow(AccountConflictException::new);
        account.setMoney(account.getMoney().add(money));
        return account;
    }

    @Override
    @Transactional
    public Account substractMoney(Long accountFromId, BigDecimal money) throws AccountNotFoundException, AccountConflictException {
        Account account = repository.findById(accountFromId).orElseThrow(AccountConflictException::new);
        //время позднее, чтобы корректно локи юзать.
        Lock lock = lockFactory.getLockByAccountId(accountFromId);
        synchronized(lock) {
            if (checkChangeMoney(account, money)) {
                account.setMoney(account.getMoney().subtract(money));
            } else {
                throw new AccountConflictException();
            }
            repository.save(account);
        }
        return account;
    }

    @Override
    @Transactional
    public void transferMoney(Long accountFromId, Long accountToId, BigDecimal money) throws AccountNotFoundException, AccountConflictException {
        Account accountFrom = repository.findById(accountFromId).orElseThrow(AccountConflictException::new);
        Account accountTo = repository.findById(accountToId).orElseThrow(AccountConflictException::new);
        Lock first,second;
        //поймем какой лочить. тем самым если у нас потенциально дедлок, мы разрешим эту ситуацию
        if (accountFromId < accountToId) {
            first = lockFactory.getLockByAccountId(accountFromId);
            second = lockFactory.getLockByAccountId(accountToId);
        } else {
            first = lockFactory.getLockByAccountId(accountToId);
            second = lockFactory.getLockByAccountId(accountFromId);
        }
        //время позднее, чтобы корректно локи юзать.
        synchronized (first) {
            synchronized (second) {
                if (checkChangeMoney(accountFrom, money)) {
                    accountFrom.setMoney(accountFrom.getMoney().subtract(money));
                } else {
                    throw new AccountConflictException();
                }
                accountTo.setMoney(accountTo.getMoney().add(money));
                repository.save(accountFrom);
                repository.save(accountTo);
            }
        }

    }


    @Override
    public boolean checkChangeMoney(Account account, BigDecimal money) {
        return account.getMoney().subtract(money).compareTo(BigDecimal.ZERO) > 0;
    }
}
