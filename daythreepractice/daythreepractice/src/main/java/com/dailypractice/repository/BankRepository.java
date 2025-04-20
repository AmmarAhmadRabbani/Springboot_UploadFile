package com.dailypractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailypractice.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

}
