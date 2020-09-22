package com.example.democlick.dto;

import java.math.BigDecimal;

/**
 * @author AkiraRokudo on 22.09.2020 in one of sun day
 */
public class AccountDto {
    //TODO: валидация

    private Long fromId;

    private Long toId;

    private BigDecimal money;

    public Long getFromId() {
        return fromId;
    }

    public Long getToId() {
        return toId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
