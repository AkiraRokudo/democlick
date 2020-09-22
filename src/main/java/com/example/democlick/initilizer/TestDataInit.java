package com.example.democlick.initilizer;

import com.example.democlick.model.Account;
import com.example.democlick.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @author AkiraRokudo on 22.09.2020 in one of sun day
 */
public class TestDataInit {

    @Autowired
    private AccountRepository repository;

    private void init()  {
        repository.save(new Account(new BigDecimal("100.40")));
        repository.save(new Account(new BigDecimal("200.30")));
    }
}
