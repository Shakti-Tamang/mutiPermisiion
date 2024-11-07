package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,String> {
    public UserModel findByEmail(String email);
    public UserModel findByUsername(String username);


}
