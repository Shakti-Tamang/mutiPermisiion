package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.Model.AddBook;
import com.nextstep.multiauhtnticate.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<AddBook,String> {
    //it is difficult approach
    public List<AddBook> findByUserToAddBook(UserModel userModel);

    @Query("SELECT b FROM AddBook b WHERE b.id = :id")
    public Optional<AddBook> findByIdExists(@Param("id") String id);
}
