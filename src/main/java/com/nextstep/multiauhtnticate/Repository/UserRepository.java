package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,String> {
    public UserModel findByEmail(String email);
    public UserModel findByUsername(String username);
    @Query("SELECT u FROM UserModel u WHERE u.id = :id")
    public Optional<UserModel> findByIdExists(@Param("id") String id);


    //it will cause native error  it is beause it is alraeady provided by jpa
//    public List<UserModel> findAllById(List<String>id);

    List<UserModel> findAllByFaculty(String faculty);



}
