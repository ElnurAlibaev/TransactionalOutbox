package com.example.outboxpattern.repository.Order;
import com.example.outboxpattern.domain.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAccountRepository extends JpaRepository<Account, Long> {
}
