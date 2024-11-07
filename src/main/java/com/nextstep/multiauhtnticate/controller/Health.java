package com.nextstep.multiauhtnticate.controller;

import com.nextstep.multiauhtnticate.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/v2")
public class Health {

    @GetMapping("getHealth")
    public ResponseEntity<ApiResponse>getHelth(){

        String check="Success";

            ApiResponse apiResponse = ApiResponse.builder().message(check).statusCode(HttpStatus.OK.value()).build();
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
