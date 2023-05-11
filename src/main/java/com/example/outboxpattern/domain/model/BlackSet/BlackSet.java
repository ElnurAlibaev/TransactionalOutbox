package com.example.outboxpattern.domain.model.BlackSet;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "blackset")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlackSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id")
    private long id;

    @Column(name = "object_Id")
    private String objectId;
}
