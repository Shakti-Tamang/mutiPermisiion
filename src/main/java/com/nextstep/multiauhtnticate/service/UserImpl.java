package com.nextstep.multiauhtnticate.service;

import com.nextstep.multiauhtnticate.Model.Role;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Repository.RoleRepo;
import com.nextstep.multiauhtnticate.Repository.UserRepository;
import com.nextstep.multiauhtnticate.utils.StringUtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepo roleRepo;

    @Override
    public void saveUser(UserModel userModel) {


        Role role=roleRepo.findByRoleName(Role.Roles.valueOf(userModel.getRoleName()));
        if(role==null){
            role=new Role();
            String hashId = StringUtills.generateRandomAlphaNumeric(10);
            role.setId(hashId);
          role.setRoleName(Role.Roles.valueOf(userModel.getRoleName()));
          roleRepo.save(role);
        }
        userModel.setUser_role(role);
        //check user exist with id or not
        if(userModel.getId()==null || userModel.getId().isEmpty()) {
            String hashId = StringUtills.generateRandomAlphaNumeric(10);
            userModel.setId(hashId);
        }
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(userModel);
    }
}
