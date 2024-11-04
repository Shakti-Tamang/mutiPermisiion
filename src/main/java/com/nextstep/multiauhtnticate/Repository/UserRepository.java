package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,String> {
    public UserModel getByEmail(String email);
}
