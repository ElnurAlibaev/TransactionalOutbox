package com.example.outboxpattern.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;

@Data
@AllArgsConstructor
public class AccountCreateDTO {

    private long id;

    @Schema(description = "valid person name", example = "John")
    private String username;

    private String password;

    @Schema(description = "valid email address", example = "john_wick@mail.ru")
    private String email;

}
