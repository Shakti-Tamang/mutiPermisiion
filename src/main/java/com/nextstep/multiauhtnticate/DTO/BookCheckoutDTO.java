package com.nextstep.multiauhtnticate.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCheckoutDTO {
    private String id;
    private LocalDateTime checkoutDate;
    private LocalDateTime dueDate;

    private UserDto user;
    private SaveBookDto book;

}
