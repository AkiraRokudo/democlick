package com.example.democlick.restcontroller;

import com.example.democlick.dto.AccountDto;
import com.example.democlick.model.Account;
import com.example.democlick.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AkiraRokudo on 22.09.2020 in one of sun day
 */
@RestController
public class AccountRestController {

    //TODO: валидация доступа. Хотя бы на списание и перевод


    private AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PutMapping("/addMoney")
    public ResponseEntity<Account> addMoney(@RequestBody AccountDto accountDto) {
        Account account = accountService.addMoney(accountDto.getFromId(), accountDto.getMoney());
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("/subtractMoney")
    public ResponseEntity<Account> subtractMoney(@RequestBody AccountDto accountDto) {
        Account account = accountService.substractMoney(accountDto.getFromId(), accountDto.getMoney());
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("/transferMoney")
    public ResponseEntity<?> transferMoney(@RequestBody AccountDto accountDto) {
        accountService.transferMoney(accountDto.getFromId(),accountDto.getToId(), accountDto.getMoney());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
