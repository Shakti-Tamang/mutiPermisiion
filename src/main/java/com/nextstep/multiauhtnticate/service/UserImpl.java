package com.nextstep.multiauhtnticate.service;

import com.nextstep.multiauhtnticate.DTO.UserDto;
import com.nextstep.multiauhtnticate.Model.Courses;
import com.nextstep.multiauhtnticate.Model.Role;

import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Repository.CourseRepo;
import com.nextstep.multiauhtnticate.Repository.RoleRepo;
import com.nextstep.multiauhtnticate.Repository.UserRepository;
import com.nextstep.multiauhtnticate.utils.StringUtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Service
public class UserImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepo roleRepo;

    @Autowired
    CourseRepo courseRepo;

    @Override
    public void saveUser(UserModel userModel1,String course) {
        try {
            List<Courses> listOfCourse = new ArrayList<>();

            // Fetch courses only if the course parameter is provided
            if (course != null && !course.trim().isEmpty()) {
                listOfCourse = courseRepo.findAllByCourseCode(course);
            }

            if (userModel1.getCourseList() == null) {
                userModel1.setCourseList(new ArrayList<>());
            }

            for (Courses courses : listOfCourse) {
                userModel1.getCourseList().add(courses);
                courses.getUsersCourse().add(userModel1);
            }

            // Handle role logic
            Role role = roleRepo.findByRoleName(Role.Roles.valueOf(userModel1.getRoleName()));
            if (role == null) {
                role = new Role();
                String hashId = StringUtills.generateRandomAlphaNumeric(10);
                role.setId(hashId);
                role.setRoleName(Role.Roles.valueOf(userModel1.getRoleName()));
                roleRepo.save(role);
            }
            userModel1.setUser_role(role);

            // Assign unique ID if it doesn't exist
            if (userModel1.getId() == null || userModel1.getId().isEmpty()) {
                String hashId = StringUtills.generateRandomAlphaNumeric(10);
                userModel1.setId(hashId);
            }

            // Encode the password
            userModel1.setPassword(passwordEncoder.encode(userModel1.getPassword()));

            // Save courses and user
            for (Courses courses : listOfCourse) {
                courseRepo.save(courses);
            }
            userRepository.save(userModel1);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("User not registered: " + ex.getMessage());
        }
    }
}
