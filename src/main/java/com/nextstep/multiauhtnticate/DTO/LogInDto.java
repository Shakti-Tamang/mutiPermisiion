package com.nextstep.multiauhtnticate.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogInDto {
    @Column(nullable = false)
    @Email(message = "must be email")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "Password must not be null")

    private String password;
}
