package com.example.outboxpattern.controller.user;

import com.example.outboxpattern.domain.dto.AccountCreateDTO;
import com.example.outboxpattern.domain.dto.AccountDTO;
import com.example.outboxpattern.domain.dto.AccountUpdateDTO;
import com.example.outboxpattern.domain.model.account.Account;
import com.example.outboxpattern.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(description = "AccountController", name = "Account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/user/accounts")
    @Operation(summary = "findAll Order accounts")
    public List<Account> findAll() {
        return accountService.findAllAccounts();
    }

    @PostMapping("/user/accounts")
    @Operation(summary = "createAccount")
    public AccountDTO createAccount(@Parameter @RequestBody AccountCreateDTO accountCreateDTO) throws JsonProcessingException {
        return accountService.createAccount(accountCreateDTO);
    }

    @PutMapping("/user/accounts/{id}")
    public AccountDTO updateAccount(@PathVariable("id") long id, @RequestBody AccountUpdateDTO accountUpdateDTO) throws JsonProcessingException {
        Optional<Account> account = accountService.findById(id);
        //System.out.println(accountUpdateDTO + " " + id);

        if (account.isPresent()) {
            return accountService.updateAccount(accountUpdateDTO, account.get());
        }

        return new AccountDTO();
    }

    @DeleteMapping("/user/accounts/{id}")
    public void deleteAccount(@PathVariable("id") long id) throws JsonProcessingException {
        Optional<Account> account = accountService.findById(id);

        if (account.isPresent()) {
            accountService.deleteAccount(account.get());
        }
    }
}
