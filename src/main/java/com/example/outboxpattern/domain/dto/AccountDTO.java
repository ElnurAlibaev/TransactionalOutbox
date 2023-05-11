package com.example.outboxpattern.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    @Schema(description = "valid person name", example = "John")
    private String username;

    private String password;

    @Schema(description = "valid email address", example = "john_wick@mail.ru")
    private String email;

    @Schema(description = "loginDate", type = "string", example = "2023-04-01T05:37:26.123456")
    private String loginDate;

}
