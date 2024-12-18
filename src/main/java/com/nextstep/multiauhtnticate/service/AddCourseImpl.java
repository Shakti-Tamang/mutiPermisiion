package com.nextstep.multiauhtnticate.service;

import com.nextstep.multiauhtnticate.DTO.CourseDTO;
import com.nextstep.multiauhtnticate.Model.Courses;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Repository.CourseRepo;
import com.nextstep.multiauhtnticate.Repository.UserRepository;
import com.nextstep.multiauhtnticate.utils.StringUtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddCourseImpl implements AddCourse {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepo courseRepo;

    @Transactional
    public void saveCourse(Courses courses, String faculty) {
        try {
            // Fetch all users belonging to the given faculty
            List<UserModel> userList = userRepository.findAllByFaculty(faculty);

            // Generate a unique course ID
            String courseId = StringUtills.generateRandomAlphaNumeric();
            courses.setCourseId(courseId);

            // Initialize usersCourse list if null
            if (courses.getUsersCourse() == null) {
                courses.setUsersCourse(new ArrayList<>());
            }

            // Link the users to the course and establish the bi-directional relationship
            for (UserModel user : userList) {
                // Add users to the course's list
                courses.getUsersCourse().add(user);

                // Add the course to each user's course list
                user.getCourseList().add(courses);

                // Save the updated user to ensure the relationship is persisted
                userRepository.save(user);
            }

            // Save the course after establishing the relationship
            courseRepo.save(courses);

        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Error occurred while saving course: " + ex.getMessage());
        }
    }

    @Override
    public List<CourseDTO> listOfCoursesByFaculty(String faculty) {
        List<Courses> list = courseRepo.findCoursesByUserFaculty(faculty);
        return list.stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    private CourseDTO mapToDTO(Courses courses) {

        CourseDTO dto = new CourseDTO();
        dto.setTitle(courses.getTitle());
        dto.setCourseCode(courses.getCourseCode());
        dto.setCourseDescription(courses.getCoursediscription());
        dto.setCourseId(courses.getCourseId());
        dto.setModeOfDelivery(courses.getModeOfDelivery());
        return  dto;

    }
}