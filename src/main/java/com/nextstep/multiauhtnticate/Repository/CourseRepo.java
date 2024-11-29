package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.Model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;



//custom method finder practice
@Repository
public interface CourseRepo extends JpaRepository<Courses,String> {


//    using all kinds of custom method finder



//    Yes, native queries in Spring Data JPA allow you to execute actual SQL queries directly on your database. This can be useful when:
    @Query(value = "SELECT c.* FROM courses c JOIN user_courses uc ON c.course_id = uc.course_id JOIN users u ON uc.user_id = u.id WHERE u.faculty = :faculty", nativeQuery = true)
    Courses findCourseByUserFaculty(String faculty);


//    you cannot create the same cutom method which is already provided by jpa
//    public List<Courses> findAllById(List<String>id);
//    this can cuase error native
}
