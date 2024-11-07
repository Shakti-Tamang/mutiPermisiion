package com.nextstep.multiauhtnticate.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id
    private String id;

    @Column(nullable = false)
    private String username;

    @ManyToOne()
    @JoinColumn(name = "user_role")
    @JsonBackReference("users_role")
    private Role user_role;

    @Column(nullable = false)
    @Email(message = "must be email")
    private String email;

    @Column(nullable = false)
    private String password;

    // New field for role name as String (this will be sent from client)
    private String roleName;
}
