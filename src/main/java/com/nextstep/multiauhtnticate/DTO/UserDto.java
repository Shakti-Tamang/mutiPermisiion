package com.nextstep.multiauhtnticate.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @Email(message = "must be email")
    private String email;


    @Column(nullable = false)
    private String password;

    private String faculty;

    // New field for role name as String (this will be sent from client)
    private String roleName;

}
