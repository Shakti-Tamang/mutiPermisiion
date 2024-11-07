package com.nextstep.multiauhtnticate.controller;

import com.nextstep.multiauhtnticate.Model.AddBook;
import com.nextstep.multiauhtnticate.Response.ApiResponse;
import com.nextstep.multiauhtnticate.service.AddBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/app/v3")
@Validated
public class AddUserBook {

    @Autowired
    AddBookService addBookService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addBook")
    public ResponseEntity<ApiResponse>addBook(@Valid @RequestBody AddBook addBook){

        addBookService.addBook(addBook);

        ApiResponse apiResponse=ApiResponse.builder().message("SuccessFullyAdded Book").statusCode(HttpStatus.OK.value()).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }
}
