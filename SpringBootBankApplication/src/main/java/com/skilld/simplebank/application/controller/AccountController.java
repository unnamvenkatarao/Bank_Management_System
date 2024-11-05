package com.skilld.simplebank.application.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilld.simplebank.application.entity.Account;
import com.skilld.simplebank.application.service.AccountServiceImpl;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired 
	AccountServiceImpl accountService;
	
	@PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }
	
	@GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountService.getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

	@GetMapping("/account/{acctID}/balance")
	public int getBalance(@PathVariable int acctID) {
		return accountService.getBalance(acctID);
	}
	
    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return accountService.deposit(id, amount);
    }

    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return accountService.withdraw(id, amount);
    }
    
    @PutMapping("/account/{acctID}/transfer/{destAcctID}/{amount}")
	public void transferAmount(@PathVariable int acctID, @PathVariable int destAcctID, @PathVariable int amount) {
		accountService.transferAmount(acctID, destAcctID, amount);
		
	}
}
