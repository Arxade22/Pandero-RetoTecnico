package com.challenge.pandero.controller;

import com.challenge.pandero.dto.Account;
import com.challenge.pandero.repository.AccountRepository;
import com.challenge.pandero.type.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    // Create
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        handleBusinessRules(account);
        return accountRepository.save(account);
    }

    // Read (all accounts)
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Read (single account)
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountRepository.findById(id).orElse(null);  // Consider better error handling
    }

    // Update
    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id, @RequestBody Account updatedAccount) {
        Account existingAccount = accountRepository.findById(id).orElse(null);  // Consider better error handling
        if (existingAccount != null) {
            existingAccount.setUsername(updatedAccount.getUsername());
            existingAccount.setPassword(updatedAccount.getPassword());
            existingAccount.setEmail(updatedAccount.getEmail());
            existingAccount.setPhoneNumber(updatedAccount.getPhoneNumber());
            existingAccount.setAccountType(updatedAccount.getAccountType());

            handleBusinessRules(existingAccount);

            return accountRepository.save(existingAccount);
        }
        return null;
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountRepository.deleteById(id);
    }

    private void handleBusinessRules(Account account) {
        sendWelcomeMail(account.getEmail());
        if (account.getAccountType() == AccountType.STANDARD || account.getAccountType() == AccountType.PREMIUM) {
            sendToPrizeService(account);
        }
        if (account.getAccountType() == AccountType.PREMIUM) {
            sendToBenefitService(account);
        }
    }

    private void sendWelcomeMail(String email) {
        // Implementación stub de envío de correo
        System.out.println("Correo de bienvenida enviado a " + email);
    }

    private void sendToPrizeService(Account account) {
        // Implementación stub para enviar a PRIZE
        System.out.println("Cuenta enviada a microservicio PRIZE: " + account.getUsername());
    }

    private void sendToBenefitService(Account account) {
        // Implementación stub para enviar a BENEFIT
        System.out.println("Cuenta enviada a microservicio BENEFIT: " + account.getUsername());
    }
}
