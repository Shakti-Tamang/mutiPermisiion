package com.nextstep.multiauhtnticate.service;

import com.nextstep.multiauhtnticate.Model.Courses;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Repository.CourseRepo;
import com.nextstep.multiauhtnticate.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AddCourseImpl implements  AddCourse {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepo courseRepo;

    @Transactional
    @Override
    public void saveCourse(Courses courses, String faculty) {
List<UserModel>list=userRepository.findAllByFaculty(faculty);

 for(UserModel userModel:list){
    userModel.getCourseList().add(courses);
   }
courseRepo.save(courses);



    }
}
