package com.nextstep.multiauhtnticate.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.util.List;


@Data
//jpa
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
@Table(name = "add_book",indexes = {
        @Index(name = "index_add_book_id", columnList = "id")})
public class AddBook {

    @ApiModelProperty(hidden = true)
    @Schema(hidden = true)

    @Id

    private String id;

    @Column(nullable = false)
    @Schema(required = true)
    private String bookTitle;

    @Column(nullable = false)
    @Schema(required = true)
    private String bookCategory;

//its object so can be nullable
    @Column(nullable = true) // nullable=true allows the field to be null
    @Schema(required = true)
    private Integer numberOfBook; // Updated field name

    @Column(nullable = false)
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

    @Schema(hidden = true)
    @OneToMany(mappedBy = "addBookCheckout",cascade = CascadeType.ALL)
    @JsonManagedReference("bookaddedCheckout")
    private List<BookCheckout>listOfCheckoutBook;



}
