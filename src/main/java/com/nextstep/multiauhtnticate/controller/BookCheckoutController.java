package com.nextstep.multiauhtnticate.controller;

import com.nextstep.multiauhtnticate.DTO.BookCheckoutDTO;
import com.nextstep.multiauhtnticate.Model.BookCheckout;
import com.nextstep.multiauhtnticate.Response.ApiResponse;
import com.nextstep.multiauhtnticate.service.BookCheckoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Valid
@RequestMapping("/app/v4")
@Tag(name = "CheckoutBook", description = "This Api is Used to Checkout Book")
public class BookCheckoutController {

    @Autowired
    BookCheckoutService bookCheckoutService;

    private Logger logger = LoggerFactory.getLogger(BookCheckoutController.class);

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('STUDENT')")
    @Operation(summary = "add record of book checkout", description = "add books checkout rexords")
    @PostMapping("/bookCheckout")
    public ResponseEntity<ApiResponse> bookChekoutBuUsers(@Valid @RequestBody BookCheckout bookCheckout, @RequestParam("user_id") String user_id, @RequestParam("book_added_id") String book_added_id) {

        bookCheckoutService.saveCheckout(bookCheckout, user_id, book_added_id);
        Authentication authentication = null;
//        List<String> listOf = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()); //take role all
        ApiResponse apiResponse = ApiResponse.builder().message("Succesfully checkout").statusCode(HttpStatus.OK.value()).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "get all checkout book with respective user adn added book", description = "this api helps to get all list of cheout book and list of the book anf the respective user with their faculty")
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAll(@RequestParam("faculty") String faculty) {

        List<BookCheckoutDTO> list = bookCheckoutService.getListOfBookCheckoutAndRespectiveUserAndAddedBookByFaculty(faculty);

        ApiResponse apiResponse = ApiResponse.<BookCheckoutDTO>builder().message("success").statusCode(HttpStatus.OK.value()).list(list).build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
