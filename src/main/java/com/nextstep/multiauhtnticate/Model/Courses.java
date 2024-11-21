package com.nextstep.multiauhtnticate.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Courses {
    @Id

    @Column(nullable = false)
    @Schema(required = true)
    private String courseId;

    @Column(nullable = false)
    @Schema(required = true)
    @NotNull(message = "must not be null")
    @NotEmpty(message = "must not be empty")

    private String title;

}
