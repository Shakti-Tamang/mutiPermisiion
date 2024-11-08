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
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/app/v3")
@Tag(name = "AddBook", description = "API for adding books") // Use @Tag instead of @Api
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
