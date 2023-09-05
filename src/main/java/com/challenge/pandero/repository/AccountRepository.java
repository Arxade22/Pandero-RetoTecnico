package com.challenge.pandero.repository;

import com.challenge.pandero.dto.Account;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AccountRepository extends JpaRepository<Account, Long> {
}

