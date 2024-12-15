package com.nextstep.multiauhtnticate.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectionBookDto {
    @Column(nullable = false)
    @ApiModelProperty(required = true)
    private String bookTitle;

    @Column(nullable = false)
    @ApiModelProperty(required = true)
    private String bookCategory;


    @Column(nullable = false)
    @ApiModelProperty(required = true)
    private Integer numberOfBook; // Updated field name

}
