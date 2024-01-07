package net.dev.admir.web;

import net.dev.admir.clients.CustomerRestClient;
import net.dev.admir.entities.BankAccount;
import net.dev.admir.model.Customer;
import net.dev.admir.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {
    private  BankAccountRepository accountRepository;
    private CustomerRestClient customerRestClient;

    public AccountRestController(BankAccountRepository accountRepository, CustomerRestClient customerRestClient) {
        this.accountRepository = accountRepository;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping("/accounts")
    public List<BankAccount> accountList() {

       List<BankAccount> accountList = accountRepository.findAll();
       accountList.forEach(acc->{
           acc.setCustomer(customerRestClient.findCustomerById(acc.getCustomerId()));
       });

       return accountList;
    }

    @GetMapping("/accounts/{id}")
    public BankAccount getAccountById(@PathVariable String id) {
        BankAccount bankAccount = accountRepository.findById(id).get();
        Customer customer = customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }
}
