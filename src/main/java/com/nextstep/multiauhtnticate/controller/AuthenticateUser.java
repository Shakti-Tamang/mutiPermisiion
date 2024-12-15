package com.nextstep.multiauhtnticate.controller;

import com.nextstep.multiauhtnticate.DTO.LogInDto;
import com.nextstep.multiauhtnticate.DTO.UserDto;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Repository.UserRepository;
import com.nextstep.multiauhtnticate.Response.ApiResponse;
import com.nextstep.multiauhtnticate.service.JwtService;
import com.nextstep.multiauhtnticate.service.UserDetailInfo;
import com.nextstep.multiauhtnticate.service.UserImpl;
import com.nextstep.multiauhtnticate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    // Constructor injection

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    // Constructor injection
    public AuthenticateUser(UserService userService,
                            AuthenticationManager authenticationManager,
                            JwtService jwtService,
                            UserRepository userRepository,
                            ModelMapper modelMapper) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.logger = LoggerFactory.getLogger(AuthenticateUser.class);
    }

//    In Spring Boot, a bean is an object managed by the Spring IoC (Inversion of Control)
//    container. Beans help organize and inject dependencies in your application, allowing
//    for easier configuration and reusability of components.

    @PostMapping("/saveUser")
    @Operation(summary = "Register User", description = "This API is used to register The user")
    public ResponseEntity<ApiResponse> saveUser(@Valid @RequestBody UserDto userDto, @RequestParam("courseCode") String courseCode) {
        UserModel userModel1 = userRepository.findByUsername(userDto.getUsername());
        if (userModel1 != null) {
            ApiResponse apiResponse = ApiResponse.builder().message("user alreday there").statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
        // Map UserDto to UserModel manually
        UserModel userModel2 = modelMapper.map(userDto, UserModel.class);

        userService.saveUser(userModel2, courseCode);
        ApiResponse apiResponse = ApiResponse.builder().message("success").statusCode(HttpStatus.OK.value()).build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/logInUser")
    @Operation(summary = "Authenticate User", description = "This API is Used To Log in The User")
    public ResponseEntity<ApiResponse> logInUser(@RequestBody LogInDto userModel) {

//        best practice than sout
        logger.error("error" + userModel.getEmail());
        logger.info("error" + userModel.getEmail());
        logger.debug("error" + userModel.getEmail());


        UserModel userModel1 = userRepository.findByEmail(userModel.getEmail());


        if (userModel1 != null) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userModel1.getUsername(), userModel.getPassword()));
            if (authentication.isAuthenticated()) {

                UserDetailInfo userDetails = (UserDetailInfo) authentication.getPrincipal();
                String token = jwtService.GenerateToken(userDetails);
                String refrenceToken = jwtService.GenerateToken(userDetails);
                ApiResponse apiResponse = ApiResponse.builder().message("success").statusCode(HttpStatus.OK.value()).Token(token).refreshToken(refrenceToken).build();

                return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
            } else {
                ApiResponse apiResponse = ApiResponse.builder().message("user not found").statusCode(HttpStatus.UNAUTHORIZED.value()).build();
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);

            }
        } else {

            ApiResponse apiResponse = ApiResponse.builder().message("unautorized access").statusCode(HttpStatus.NOT_FOUND.value()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);

        }

    }

}
