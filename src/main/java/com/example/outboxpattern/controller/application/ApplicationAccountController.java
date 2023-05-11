package com.example.outboxpattern.controller.application;

import com.example.outboxpattern.domain.model.account.Account;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.outboxpattern.service.AccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(description = "AccountController", name = "Account")
public class ApplicationAccountController {
    /*private final AccountService accountService;
    @GetMapping("/application/accounts")
    @Operation(summary = "findAll accounts")
    public List<Account> findAll() {
        return accountService.findAllAccounts();
    }*/
}
