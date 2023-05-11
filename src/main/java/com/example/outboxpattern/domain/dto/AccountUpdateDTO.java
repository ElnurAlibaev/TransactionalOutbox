package com.example.outboxpattern.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountUpdateDTO {
    @Schema(description = "valid person name", example = "John")
    private String username;

    private String password;

    @Schema(description = "valid email address", example = "john_wick@mail.ru")
    private String email;

}
