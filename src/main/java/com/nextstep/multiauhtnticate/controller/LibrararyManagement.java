package com.nextstep.multiauhtnticate.controller;

import com.google.api.Http;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Repository.UserRepository;
import com.nextstep.multiauhtnticate.Response.ApiResposne;
import com.nextstep.multiauhtnticate.service.JwtService;
import com.nextstep.multiauhtnticate.service.UserDetailInfo;
import com.nextstep.multiauhtnticate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;

@RestController
@Validated
@RequestMapping("/app/v1")
public class LibrararyManagement {
//json to dart and dart to json serializer and deserializer
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

//    In Spring Boot, a bean is an object managed by the Spring IoC (Inversion of Control)
//    container. Beans help organize and inject dependencies in your application, allowing
//    for easier configuration and reusability of components.

    @PostMapping("/saveUser")
    public ResponseEntity<ApiResposne>saveUser(@Valid @RequestBody UserModel userModel){

        userService.saveUser(userModel);

        ApiResposne apiResposne=ApiResposne.builder().message("success").statusCode(HttpStatus.OK.value()).build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResposne);
    }

    @PostMapping("/logInUser")
    public ResponseEntity<ApiResposne>logInUser(@RequestBody UserModel userModel){

        UserModel user=userRepository.findByEmail( userModel.getEmail());
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),userModel.getPassword()));
        if(authentication.isAuthenticated()){
            UserDetailInfo userDetails = (UserDetailInfo) authentication.getPrincipal();
            String token=jwtService.GenerateToken(userDetails);

            ApiResposne apiResposne=ApiResposne.builder().message("success").statusCode(HttpStatus.OK.value()).Token( token).build();

            return ResponseEntity.status(HttpStatus.OK).body(apiResposne);
        }
        else{
            ApiResposne apiResposne=ApiResposne.builder().message("unautorized access").statusCode(HttpStatus.UNAUTHORIZED.value()).build();
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResposne);

        }

    }

}
