package com.nextstep.multiauhtnticate.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nextstep.multiauhtnticate.Model.Role;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveBookDto {


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

}
