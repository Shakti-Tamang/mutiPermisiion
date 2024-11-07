package com.nextstep.multiauhtnticate.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AddBook {
    @Id

    private String id;

    @Column(nullable = false)
    @ApiModelProperty(required = true)
    private String bookTitle;

    @Column(nullable = false)
    @ApiModelProperty(required = true)
    private String bookCategory;

    @Column(nullable = false)
    @ApiModelProperty(required = true)
    private int bootQuantity;

    @Column(nullable = false)
    @ApiModelProperty(required = true)
    private String availability;



    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonBackReference("userAddBook")
    @ApiModelProperty(hidden = true)
    private UserModel userToAddBook;

}
