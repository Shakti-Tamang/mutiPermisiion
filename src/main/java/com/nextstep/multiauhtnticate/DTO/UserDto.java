package com.nextstep.multiauhtnticate.DTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nextstep.multiauhtnticate.Model.AddBook;
import com.nextstep.multiauhtnticate.Model.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

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

    // New field for role name as String (this will be sent from client)
    private String roleName;

}
