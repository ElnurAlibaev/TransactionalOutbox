package com.example.outboxpattern.repository.BlackSetRepository;
import com.example.outboxpattern.domain.model.BlackSet.BlackSet;
import com.example.outboxpattern.domain.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackSetRepository extends JpaRepository<BlackSet, Long> {

}
