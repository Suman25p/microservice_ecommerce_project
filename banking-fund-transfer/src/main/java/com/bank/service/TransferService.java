package com.bank.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.dto.TransferRequest;
import com.bank.entity.Account;
import com.bank.entity.TransactionHistory;
import com.bank.exception.InsufficientBalanceException;
import com.bank.exception.ResourceNotFoundException;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransferService {

	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;

	// Constructor Injection
	public TransferService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
	}

	@Transactional
	public String transferMoney(TransferRequest request) {

		// Check same account
		if (request.getFromAccount().equals(request.getToAccount())) {
			throw new IllegalArgumentException("Cannot transfer to same account.");
		}

		// Fetch Sender
		Account sender = accountRepository.findById(request.getFromAccount())
				.orElseThrow(() -> new ResourceNotFoundException("Sender account not found"));

		// Fetch Receiver
		Account receiver = accountRepository.findById(request.getToAccount())
				.orElseThrow(() -> new ResourceNotFoundException("Receiver account not found"));

		// Validate Amount
		if (request.getAmount() <= 0) {
			throw new IllegalArgumentException("Invalid Amount");
		}

		// Check Balance
		if (sender.getBalance() < request.getAmount()) {
			throw new InsufficientBalanceException("Insufficient Balance");
		}

		// Debit Sender
		sender.setBalance(sender.getBalance() - request.getAmount());

		// Credit Receiver
		receiver.setBalance(receiver.getBalance() + request.getAmount());

		// Save Updated Accounts
		accountRepository.save(sender);
		accountRepository.save(receiver);

		// Save Transaction History
		TransactionHistory transaction = new TransactionHistory();

		transaction.setFromAccount(sender.getId());
		transaction.setToAccount(receiver.getId());
		transaction.setAmount(request.getAmount());
		transaction.setTransactionTime(LocalDateTime.now());
		transaction.setStatus("SUCCESS");

		transactionRepository.save(transaction);

		return "Money Transferred Successfully";
	}

	// Get Transaction History
	public List<TransactionHistory> getTransactionHistory(Long accountId) {

		return transactionRepository.findByFromAccountOrToAccount(accountId, accountId);

	}

}