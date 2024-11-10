package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,String> {
    public UserModel findByEmail(String email);
    public UserModel findByUsername(String username);
    @Query("SELECT u FROM UserModel u WHERE u.id = :id")
    public Optional<UserModel> findByIdExists(@Param("id") String id);


}
