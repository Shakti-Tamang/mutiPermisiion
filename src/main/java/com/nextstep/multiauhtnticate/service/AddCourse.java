package com.nextstep.multiauhtnticate.service;

import com.nextstep.multiauhtnticate.DTO.CourseDTO;
import com.nextstep.multiauhtnticate.Model.Courses;

import java.util.List;

public interface AddCourse {
    public void saveCourse(Courses courses, String faculty);
    public List<CourseDTO>listOfCoursesByFaculty(String faculty);

//    public List<ourse>
}
