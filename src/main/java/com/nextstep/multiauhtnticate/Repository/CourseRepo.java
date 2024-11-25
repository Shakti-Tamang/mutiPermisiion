package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.Model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Courses,String> {
}
