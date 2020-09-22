package com.example.democlick.repositories;

import com.example.democlick.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author AkiraRokudo on 22.09.2020 in one of sun day
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

}
