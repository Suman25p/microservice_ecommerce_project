package com.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entity.TransactionHistory;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionHistory, Long>{
	 List<TransactionHistory> findByFromAccountOrToAccount(Long from, Long to);
}
