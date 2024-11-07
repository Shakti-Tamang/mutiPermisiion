package com.nextstep.multiauhtnticate.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class Role {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles roleName; // Enum type for role (e.g., ADMIN, USER)

    @OneToMany(mappedBy = "user_role", cascade = CascadeType.ALL)
    @JsonManagedReference("users_role")
    private List<UserModel> users;

    public enum Roles {
        ADMIN,
        STUDENT,
        TEACHER,
        LIBRARIAN
    }
}