package com.nextstep.multiauhtnticate.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fine")
public class FineModel {

    @Schema(hidden = true)
    @Id
    private String id;


    //small int
    @Column(nullable = false)
    @Schema(required = true)
    private Short fine;

    @OneToOne()
    @Schema(hidden = true)
    @JsonBackReference("fineUser")
    private UserModel userFine;

}
