package com.nextstep.multiauhtnticate.controller;

import com.nextstep.multiauhtnticate.DTO.LogInDto;
import com.nextstep.multiauhtnticate.DTO.UserDto;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Repository.UserRepository;
import com.nextstep.multiauhtnticate.Response.ApiResponse;
import com.nextstep.multiauhtnticate.service.JwtService;
import com.nextstep.multiauhtnticate.service.UserDetailInfo;
import com.nextstep.multiauhtnticate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/app/v1")
@Tag(name = "Authenticate User", description = "Registaring authenticated user") // Use @Tag instead of @Api
public class AuthenticateUser {
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
    @Operation(summary = "Register User",description = "This API is used to register The user")
    public ResponseEntity<ApiResponse>saveUser(@Valid @RequestBody UserDto userModel){
        UserModel userModel1=userRepository.findByUsername(userModel.getUsername());

        // Map UserDto to UserModel manually
        UserModel userModel2 = new UserModel();

        userModel2.setUsername(userModel.getUsername());
        userModel2.setEmail(userModel.getEmail());
        userModel2.setPassword(userModel.getPassword());
        userModel2.setRoleName(userModel.getRoleName());
        userService.saveUser(userModel2);
        if(userModel1 !=null){
            ApiResponse apiResponse = ApiResponse.builder().message("user alreday there").statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }

        ApiResponse apiResponse = ApiResponse.builder().message("success").statusCode(HttpStatus.OK.value()).build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/logInUser")
    @Operation(summary = "Authenticate User",description = "This API is Used To Log in The User")
    public ResponseEntity<ApiResponse>logInUser(@RequestBody LogInDto userModel){
        System.out.println(userModel.getEmail());
        UserModel userModel1=userRepository.findByEmail(userModel.getEmail());


        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userModel1.getUsername(),userModel.getPassword()));
        if(authentication.isAuthenticated()){
            UserDetailInfo userDetails = (UserDetailInfo) authentication.getPrincipal();
            String token=jwtService.GenerateToken(userDetails);
             String refrenceToken=jwtService.GenerateToken(userDetails);
            ApiResponse apiResponse = ApiResponse.builder().message("success").statusCode(HttpStatus.OK.value()).Token( token).refreshToken(refrenceToken).build();

            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        else{
            ApiResponse apiResponse = ApiResponse.builder().message("unautorized access").statusCode(HttpStatus.UNAUTHORIZED.value()).build();
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);

        }

    }

}
