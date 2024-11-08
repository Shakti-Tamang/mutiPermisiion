package com.nextstep.multiauhtnticate.DTO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookDto {
    @Column(nullable = true) // Change to true
    @Schema(required = true) // Change required to false
    private String bookTitle;

    @Column(nullable = true) // Change to true
    @Schema(required = true) // Change required to false
    private String bookCategory;

    @Column(nullable = true) // Change to true
    @Schema(required = true) // Change required to false
    private Integer bootQuantity; // Change from int to Integer

    @Column(nullable = true) // Change to true
    @Schema(required = true) // Change required to false
    private String availability;

}
