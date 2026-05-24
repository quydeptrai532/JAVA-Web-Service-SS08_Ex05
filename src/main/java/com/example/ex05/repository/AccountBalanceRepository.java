package com.example.ex05.repository;

import com.example.ex05.model.entity.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountBalanceRepository extends JpaRepository<AccountBalance, String> {
}