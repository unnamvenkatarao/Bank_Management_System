package com.skilld.simplebank.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilld.simplebank.application.entity.Account;
import com.skilld.simplebank.application.repository.AccountRepository;

@Service
public class AccountServiceImpl {

	@Autowired
	AccountRepository accountRepository;
	
	public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    public Account deposit(Long id, double amount) {
        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }

    public Account withdraw(Long id, double amount) {
        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }
    
    public int getBalance(int acctID) {
		return accountRepository.findBalanceByAcctID(acctID);
	}
    
    public void transferAmount(int acctID, int destAcctID, int amount) {
		accountRepository.withdrawAmountByAcctID(acctID, amount);
		accountRepository.saveBalanceByAcctID(destAcctID, amount);
	}
    
    
}
