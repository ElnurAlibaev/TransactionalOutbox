package com.example.outboxpattern.service;

import com.example.outboxpattern.domain.dto.AccountUpdateDTO;
import com.example.outboxpattern.domain.model.BlackSet.BlackSet;
import com.example.outboxpattern.domain.model.account.Account;
import com.example.outboxpattern.domain.dto.AccountCreateDTO;
import com.example.outboxpattern.domain.dto.AccountDTO;
import com.example.outboxpattern.domain.model.event.Event;
import com.example.outboxpattern.repository.BlackSetRepository.BlackSetRepository;
import com.example.outboxpattern.repository.Order.OrderAccountRepository;
//import com.example.outboxpattern.repository.Outbox.OutboxAccountRepository;
import com.example.outboxpattern.repository.Outbox.OutboxAccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AccountService {
    @Autowired
    private final OrderAccountRepository orderAccountRepository;

    @Autowired
    private final OutboxAccountRepository outboxAccountRepository;

    @Autowired
    private final BlackSetRepository blackSetRepository;

    private final NewTopic topic1;

    private final ApplicationEventPublisher eventPublisher;

    public List<Account> findAllAccounts() {
        return orderAccountRepository.findAll();
    }

    public Optional<Account> findById(long id) {
        return orderAccountRepository.findById(id);
    }
    public AccountDTO createAccount(AccountCreateDTO accountCreateDTO) throws JsonProcessingException {
        Account account = new Account();

        Event event = new Event();
        ObjectMapper mapper = new ObjectMapper();
        event.setStateBefore(mapper.writeValueAsString(account));

        account.setEmail(accountCreateDTO.getEmail());
        account.setPassword(accountCreateDTO.getPassword());
        account.setUsername(accountCreateDTO.getUsername());

        Account savedAccount = orderAccountRepository.save(account);

        event.setEmittedDate(LocalDateTime.now());
        event.setObjectType("Account");
        event.setRequestType("Create");
        event.setObjectId(String.valueOf(savedAccount.getId()));
        event.setStateAfter(mapper.writeValueAsString(savedAccount));

        Event savedAccountEvent = outboxAccountRepository.save(event);

        return new AccountDTO(
                savedAccount.getUsername(),
                savedAccount.getPassword(),
                savedAccount.getEmail(),
                savedAccount.getLast_login().toString());
    }

    public AccountDTO updateAccount(AccountUpdateDTO accountUpdateDTO, Account account) throws JsonProcessingException {
        Event event = new Event();
        ObjectMapper mapper = new ObjectMapper();
        event.setStateBefore(mapper.writeValueAsString(account));

        account.setEmail(accountUpdateDTO.getEmail());
        account.setPassword(accountUpdateDTO.getPassword());
        account.setUsername(accountUpdateDTO.getUsername());

        account.setLast_login(LocalDateTime.now());

        Account savedAccount = orderAccountRepository.save(account);

        event.setEmittedDate(LocalDateTime.now());
        event.setObjectType("Account");
        event.setRequestType("Update");
        event.setObjectId(String.valueOf(savedAccount.getId()));
        event.setStateAfter(mapper.writeValueAsString(savedAccount));

        Event updatedAccountEvent = outboxAccountRepository.save(event);

        return new AccountDTO(
                savedAccount.getUsername(),
                savedAccount.getPassword(),
                savedAccount.getEmail(),
                savedAccount.getLast_login().toString());
    }

    public void deleteAccount(Account account) throws JsonProcessingException {
        Event event = new Event();
        ObjectMapper mapper = new ObjectMapper();
        event.setStateBefore(mapper.writeValueAsString(account));

        orderAccountRepository.deleteById(account.getId());

        event.setEmittedDate(LocalDateTime.now());
        event.setObjectType("Account");
        event.setRequestType("Delete");
        event.setObjectId(String.valueOf(account.getId()));
        event.setStateAfter(mapper.writeValueAsString(new Account()));

        Event deletedAccountEvent = outboxAccountRepository.save(event);
    }

    @KafkaListener(topicPattern = "msg_broker")
    public void listenGroupBroker(String message) {
        System.out.println("Received Message in group msg_broker: " + message);
    }
}
