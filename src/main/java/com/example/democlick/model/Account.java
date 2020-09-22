package com.example.democlick.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author AkiraRokudo on 22.09.2020 in one of sun day
 */
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "money", precision = 19, scale = 4)
    private BigDecimal money;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Account() {
    }

    public Account(BigDecimal money) {
        this.money = money;
    }
}
