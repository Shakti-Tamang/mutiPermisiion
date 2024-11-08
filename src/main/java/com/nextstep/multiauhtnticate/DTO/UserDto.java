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
    @Schema(hidden = true)
    @Id
    private String id;

    @Column(nullable = false)
    private String username;

    //    @ApiModelProperty(hidden = true)
    @Schema(hidden = true)
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

    //    @ApiModelProperty(hidden = true)
    @Schema(hidden = true)
    @OneToMany(mappedBy = "userToAddBook",cascade = CascadeType.ALL)
    @JsonManagedReference("userAddBook")
    private List<SaveBookDto> listOfBook;

}
