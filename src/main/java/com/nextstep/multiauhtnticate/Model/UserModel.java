package com.nextstep.multiauhtnticate.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserModel {

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

    @Column(nullable = true)
    private String faculty;

//    @ApiModelProperty(hidden = true)
    @Schema(hidden = true)
    @OneToMany(mappedBy = "userToAddBook",cascade = CascadeType.ALL)
    @JsonManagedReference("userAddBook")
    private List<AddBook> listOfBook;

    @Schema(hidden = true)
    @OneToMany(mappedBy = "usersBook",cascade = CascadeType.ALL)
    @JsonManagedReference("bookCheckout")
    private List<BookCheckout>listOfBookCheckout;

    @Schema(hidden = true)
    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference("fineUser")
    private FineModel fineUser;

    @Schema(hidden = true)
    @Column(nullable = false)
    @ManyToMany(mappedBy = "usersCourse", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("usersCourse")
    private List<Courses>courseList;

}

