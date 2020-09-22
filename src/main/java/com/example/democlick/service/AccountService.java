package com.example.democlick.service;

import com.example.democlick.dto.AccountDto;
import com.example.democlick.exception.AccountConflictException;
import com.example.democlick.exception.AccountNotFoundException;
import com.example.democlick.model.Account;

import java.math.BigDecimal;

/**
 * @author AkiraRokudo on 22.09.2020 in one of sun day
 */
public interface AccountService {

    Account addMoney(Long accountFromId, BigDecimal money) throws AccountNotFoundException;

    Account substractMoney(Long accountFromId, BigDecimal money) throws AccountNotFoundException, AccountConflictException;

    void transferMoney(Long accountFromId, Long accountToId, BigDecimal money) throws AccountNotFoundException, AccountConflictException;

    boolean checkChangeMoney(Account account, BigDecimal money);
}
