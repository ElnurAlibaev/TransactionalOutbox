package com.example.outboxpattern.repository.Outbox;
import com.example.outboxpattern.domain.model.account.Account;
import com.example.outboxpattern.domain.model.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxAccountRepository extends JpaRepository<Event, Long> {
}
