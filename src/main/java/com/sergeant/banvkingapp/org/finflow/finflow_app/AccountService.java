package com.sergeant.banvkingapp.org.finflow.finflow_app;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;


    @Transactional
    public AccountEntity createAccount(AccountEntity account){
        return accountRepository.save(account);
    }

    public Optional<AccountEntity> getAccount(Long id){
        return  accountRepository.findById(id);
    }

    @Transactional
    public AccountEntity deposit(Long id, double amount){
        AccountEntity account = getAccount(id).orElseThrow(() ->new RuntimeException("Account is not found"));
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);

    }

    @Transactional
    public AccountEntity withdraw(Long id, double amount){
        AccountEntity account = getAccount(id).orElseThrow(()-> new RuntimeException("Account Does not Exist"));

        if (account.getBalance() < amount){
            throw  new RuntimeException("Insufficient funds!!! ");
        }

        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }
}
