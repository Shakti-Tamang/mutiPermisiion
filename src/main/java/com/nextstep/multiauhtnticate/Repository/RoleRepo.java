package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,String> {
    Role findByRoleName(Role.Roles roleName);
}
