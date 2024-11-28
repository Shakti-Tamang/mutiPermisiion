package com.nextstep.multiauhtnticate.service;

import com.nextstep.multiauhtnticate.DTO.UserDto;
import com.nextstep.multiauhtnticate.Model.Role;

import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Repository.RoleRepo;
import com.nextstep.multiauhtnticate.Repository.UserRepository;
import com.nextstep.multiauhtnticate.utils.StringUtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class UserImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepo roleRepo;

    @Override
    public void saveUser(UserModel userModel1) {

try {
    Role role = roleRepo.findByRoleName(Role.Roles.valueOf(userModel1.getRoleName()));
    if (role == null) {
        role = new Role();
        String hashId = StringUtills.generateRandomAlphaNumeric(10);
        role.setId(hashId);
        role.setRoleName(Role.Roles.valueOf(userModel1.getRoleName()));
        roleRepo.save(role);
    }
    userModel1.setUser_role(role);
    //check user exist with id or not
    if (userModel1.getId() == null || userModel1.getId().isEmpty()) {
        String hashId = StringUtills.generateRandomAlphaNumeric(10);
        userModel1.setId(hashId);
    }
    userModel1.setPassword(passwordEncoder.encode(userModel1.getPassword()));
    userRepository.save(userModel1);
}
catch (Exception ex){
    ex.printStackTrace();
    throw  new RuntimeException("user not registered"+ex.getMessage());
}
    }
}
