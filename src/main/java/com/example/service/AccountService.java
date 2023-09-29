package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    public AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account registerUser(Account account) {
        if (account.getUsername().length() > 0 && account.getPassword().length() > 4 && accountRepository.findByUsername(account.getUsername()).isEmpty())
            return accountRepository.save(account);
        return null;
    }

    public Account loginUser(Account account) {
        Optional<Account> optAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (optAccount.isPresent())
            return optAccount.get();
        return null;
    }

}
