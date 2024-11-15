package com.nextstep.multiauhtnticate.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_checkout")
public class BookCheckout {

@Schema(hidden = true)
    @Id

    private String id;


@Column(nullable = false)
@Schema(required = true)
private LocalDateTime checkoutDate;

@Column(nullable = false)
@Schema(required = true)
private LocalDateTime dueDate;



 @Schema(hidden = true)
   @ManyToOne()
    @JoinColumn(name = "users_id")
    @JsonBackReference("bookCheckout")
    private UserModel usersBook;

@Schema(hidden = true)
 @ManyToOne()
    @JoinColumn(name="add_book_id")
    @JsonBackReference("bookaddedCheckout")
    private AddBook addBookCheckout;
}
