package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.Model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



//custom method finder practice
@Repository
public interface CourseRepo extends JpaRepository<Courses,String> {


//    using all kinds of custom method finder


//    you cannot create the same cutom method which is already provided by jpa
//    public List<Courses> findAllById(List<String>id);
//    this can cuase error native
}
