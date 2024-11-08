package com.nextstep.multiauhtnticate.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AddBook {

//    @ApiModelProperty(hidden = true)
    @Schema(hidden = true)
    @Id

    private String id;

    @Column(nullable = true)
    @Schema(required = true)
    private String bookTitle;

    @Column(nullable = true)
    @Schema(required = true)
    private String bookCategory;

    @Column(nullable = true)
    @Schema(required = true)
    private int bootQuantity;

    @Column(nullable = true)
    @Schema(required = true)
    private String availability;



    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonBackReference("userAddBook")
    //this for lower version
//    @ApiModelProperty(hidden = true)

    //this for new version
    @Schema(hidden = true)

    private UserModel userToAddBook;

}
