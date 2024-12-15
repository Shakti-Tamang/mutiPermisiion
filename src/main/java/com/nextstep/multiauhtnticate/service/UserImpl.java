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
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Service
public class UserImpl implements UserService {

    //field and setteer injection
//    @Autowired
//    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
//    @Autowired
//    RoleRepo roleRepo;
//
//    @Autowired
//    CourseRepo courseRepo;

    private final UserRepository userRepository;
    private final RoleRepo roleRepo;
    private final CourseRepo courseRepo;

    public UserImpl( UserRepository userRepository, RoleRepo roleRepo, CourseRepo courseRepo) {
        this.userRepository = userRepository;
        this.roleRepo = roleRepo;
        this.courseRepo = courseRepo;
    }

    @Transactional
    @Override
    public void saveUser(UserModel userModel1, String courseCode) {
        try {
            // Fetching the course from the database
            List<Courses> listOfCourse = courseRepo.findByCourseCode(courseCode);

            // Initialize courseList if null
            if (userModel1.getCourseList() == null) {
                userModel1.setCourseList(new ArrayList<>());
            }

            // Add courses to the user's courseList
            for (Courses courses : listOfCourse) {
                userModel1.getCourseList().add(courses);
                courses.getUsersCourse().add(userModel1);  // Ensure courses know about the user
            }

            // Set role if necessary
            Role role = roleRepo.findByRoleName(Role.Roles.valueOf(userModel1.getRoleName()));
            if (role == null) {
                role = new Role();
                String hashId = StringUtills.generateRandomAlphaNumeric(10);
                role.setId(hashId);
                role.setRoleName(Role.Roles.valueOf(userModel1.getRoleName()));
                roleRepo.save(role);
            }
            userModel1.setUser_role(role);

            // Generate unique ID if missing
            if (userModel1.getId() == null || userModel1.getId().isEmpty()) {
                String hashId = StringUtills.generateRandomAlphaNumeric(10);
                userModel1.setId(hashId);
            }

            userModel1.setPassword(passwordEncoder.encode(userModel1.getPassword()));

            // Save the user model and related entities
            userRepository.save(userModel1);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("User not registered: " + ex.getMessage());
        }
    }
}