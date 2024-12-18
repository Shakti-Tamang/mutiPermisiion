package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.Model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;



//custom method finder practice
@Repository
public interface CourseRepo extends JpaRepository<Courses,String> {


//    using all kinds of custom method finder



//    Yes, native queries in Spring Data JPA allow you to execute actual SQL queries directly on your database. This can be useful when:
    @Query(value = "SELECT c.* FROM courses c JOIN user_courses uc ON c.course_id = uc.course_id JOIN users u ON uc.user_id = u.id WHERE u.faculty = :faculty", nativeQuery = true)
    Courses findCourseByUserFaculty(String faculty);

//
//    Key Points About the Query
//    JOIN c.usersCourse:
//
//    The Courses entity has a usersCourse field, which is a @ManyToMany relationship to UserModel.
//    This join navigates the relationship from the Courses entity to the associated UserModel entities.
//    Alias u:
//
//    u is an alias for UserModel, representing the users associated with the course.
//    Filter WHERE u.faculty = :faculty:
//
//    Adds a condition to only consider users whose faculty field matches the provided value (:faculty).
//    SELECT c:
//
//    Selects the Courses entity (the root of the query), resulting in a list of courses that are associated with users in the specified faculty.
//

    //
//    //jpql
    @Query("SELECT DISTINCT c FROM Courses c LEFT JOIN c.usersCourse u WHERE u.faculty = :faculty")
    @QueryHints({
            @QueryHint(name = "org.hibernate.readOnly", value = "true"),
            @QueryHint(name = "org.hibernate.fetchSize", value = "50"),
            @QueryHint(name = "org.hibernate.cacheable", value = "true"),
            @QueryHint(name = "javax.persistence.cache.retrieveMode", value = "USE"),
            @QueryHint(name = "javax.persistence.cache.storeMode", value = "USE"),
            @QueryHint(name = "javax.persistence.query.timeout", value = "2000")
    })
    List<Courses> findCoursesByUserFaculty(@Param("faculty") String faculty);

    List<Courses> findByCourseCode(String code);


}
