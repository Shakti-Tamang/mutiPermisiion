package com.nextstep.multiauhtnticate.controller;

import com.nextstep.multiauhtnticate.Model.Courses;
import com.nextstep.multiauhtnticate.Response.ApiResponse;
import com.nextstep.multiauhtnticate.service.AddCourse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/app/v3")
@Tag(name = "addCourse",description = "to add courses")
public class CourseAdd {

    private Logger logger= LoggerFactory.getLogger(CourseAdd.class);

    @Autowired
    AddCourse addCourse;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "post Api To Add Book",description = "add course ")
    @PostMapping("/addCourse")
    public ResponseEntity<ApiResponse> addCourses(@RequestParam("faculty")String faculty, @Valid @RequestBody Courses courses){
  addCourse.saveCourse(courses,faculty);

  ApiResponse apiResponse=ApiResponse.builder().message("successfull saved courses").statusCode(HttpStatus.OK.value()).build();
  return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
