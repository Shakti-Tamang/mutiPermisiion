package com.nextstep.multiauhtnticate.controller;

import com.google.api.Http;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Response.ApiResposne;
import com.nextstep.multiauhtnticate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("multi/app/v1")
public class LibrararyManagement {

    @Autowired
    UserService userService;

//    In Spring Boot, a bean is an object managed by the Spring IoC (Inversion of Control)
//    container. Beans help organize and inject dependencies in your application, allowing
//    for easier configuration and reusability of components.

    @PostMapping("save user")
    public ResponseEntity<ApiResposne>saveUser(@Valid @RequestBody UserModel userModel){

        userService.saveUser(userModel);
        ApiResposne apiResposne=ApiResposne.builder().message("success").statusCode(HttpStatus.OK.value()).build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResposne);
    }

}
