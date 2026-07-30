package com.bank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.TransferRequest;
import com.bank.entity.TransactionHistory;
import com.bank.service.TransferService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;

    // Constructor Injection
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    // Transfer Money API
    @PostMapping
    public ResponseEntity<String> transferMoney(@Valid @RequestBody TransferRequest request) {

        return ResponseEntity.ok(transferService.transferMoney(request));

    }

    // Transaction History API
    @GetMapping("/history/{accountId}")
    public ResponseEntity<List<TransactionHistory>> getTransactionHistory(@PathVariable Long accountId) {

        return ResponseEntity.ok(transferService.getTransactionHistory(accountId));

    }

}