package com.nextstep.multiauhtnticate.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Data
@Entity
public class UserModel {
    @Id
    private String id;


    @Column(nullable = false)
    @ApiModelProperty(required = true)
    private String username;

    @Column(nullable = false)
    @ApiModelProperty(required = true)
    private String role;

    @Column(nullable = false)
    @Email(message = "must be email")
    @ApiModelProperty(required = true)
    private String email;

    @Column(nullable = false)
    @ApiModelProperty(required = true)
    private String password;


}
