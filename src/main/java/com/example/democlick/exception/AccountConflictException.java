package com.example.democlick.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author AkiraRokudo on 18.09.2020 in one of sun day
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class AccountConflictException extends RuntimeException {
    public AccountConflictException() {
        super();
    }
}