package com.bank.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class TransferRequest {

	@NotNull(message = "From Account cannot be null")
	private Long fromAccount;

	@NotNull(message = "To Account cannot be null")
	private Long toAccount;

	@NotNull(message = "Amount cannot be null")
	@DecimalMin(value = "1.0", message = "Amount must be greater than 0")
	private Double amount;

	// Default Constructor
	public TransferRequest() {
	}

	public Long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Long getToAccount() {
		return toAccount;
	}

	public void setToAccount(Long toAccount) {
		this.toAccount = toAccount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}