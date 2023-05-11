package com.example.outboxpattern.domain.model.event;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_events")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id")
    private long id;

    @Column(name = "object_type")
    private String objectType;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "object_Id")
    private String objectId;

    @Column(name = "state_before")
    private String stateBefore;

    @Column(name = "state_after")
    private String stateAfter;

    @CreationTimestamp
    @Column(name = "emitted_date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime emittedDate;

}
