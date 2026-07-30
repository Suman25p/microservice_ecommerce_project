package com.bank.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.bank.dto.AccountRequest;
import com.bank.dto.AccountResponse;
import com.bank.entity.Account;
import com.bank.exception.ResourceNotFoundException;
import com.bank.repository.AccountRepository;

@Service
public class AccountService {

	private final AccountRepository accountRepository;

	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	// Create Account
	public AccountResponse createAccount(AccountRequest request) {

		Account account = new Account();

		account.setCustomerName(request.getCustomerName());
		account.setBalance(request.getInitialBalance());
		account.setAccountNumber(generateAccountNumber());
		account.setCreatedAt(LocalDateTime.now());

		Account savedAccount = accountRepository.save(account);

		return mapToResponse(savedAccount);
	}

	// Get Account By Id
	public AccountResponse getAccount(Long id) {

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found"));

		return mapToResponse(account);
	}

	// Get All Accounts
	public List<Account> getAllAccounts() {

		return accountRepository.findAll();

	}

	// Generate Random Account Number
	private String generateAccountNumber() {

		Random random = new Random();

		return "ACC" + (100000 + random.nextInt(900000));

	}

	// Convert Entity to DTO
	private AccountResponse mapToResponse(Account account) {

		AccountResponse response = new AccountResponse();

		response.setId(account.getId());
		response.setAccountNumber(account.getAccountNumber());
		response.setCustomerName(account.getCustomerName());
		response.setBalance(account.getBalance());

		return response;
	}

}