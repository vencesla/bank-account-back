package net.dev.admir.web;

import net.dev.admir.entities.BankAccount;
import net.dev.admir.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {
    BankAccountRepository accountRepository;

    public AccountRestController(BankAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/accounts")
    public List<BankAccount> accountList() {
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount getAccountById(@PathVariable String id) {
        return accountRepository.findById(id).get();
    }
}
