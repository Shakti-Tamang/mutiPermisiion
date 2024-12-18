package com.nextstep.multiauhtnticate.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDTO {
    private String courseId;
    private String title;
    private String courseCode;
    private String courseDescription;
    private String modeOfDelivery;
}
