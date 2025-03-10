package com.sergeant.banvkingapp.org.finflow.finflow_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public AccountEntity createAccount(@RequestBody AccountEntity account){
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public AccountEntity getAccount(@PathVariable Long id){
        return accountService.getAccount(id).orElseThrow(()-> new RuntimeException("Account doesn't Exist!!!"));

    }

    @PostMapping("/{id}/deposit")
    public AccountEntity deposit(@PathVariable Long id, @RequestBody Map<String, Double> request ){
       Double amount = request.get("amount");
       return  accountService.deposit(id,amount);
    }

    @PostMapping("/{id}/withdraw")
    public AccountEntity withdraw(@PathVariable Long id, @RequestBody Map<String, Double>request){

        Double amount = request.get("amount");
        return accountService.withdraw(id, amount);

    }

}
