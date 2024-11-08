package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.Model.AddBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<AddBook,String> {
}
