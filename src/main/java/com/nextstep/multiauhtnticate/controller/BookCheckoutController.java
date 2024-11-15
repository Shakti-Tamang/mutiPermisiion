package com.nextstep.multiauhtnticate.controller;

import com.nextstep.multiauhtnticate.Model.BookCheckout;
import com.nextstep.multiauhtnticate.Response.ApiResponse;
import com.nextstep.multiauhtnticate.service.BookCheckoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "CheckoutBook",description = "This Api is Used to Checkout Book")
public class BookCheckoutController {

    @Autowired
    BookCheckoutService bookCheckoutService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "add record of book checkout",description = "add books checkout rexords")
    @PostMapping("/bookCheckout")
    public ResponseEntity<ApiResponse>bookChekoutBuUsers(@Valid @RequestBody BookCheckout bookCheckout,@RequestParam("user_id") String user_id, @RequestParam("book_added_id") String book_added_id){

        bookCheckoutService.saveCheckout(bookCheckout,user_id,book_added_id);
        Authentication authentication=null;
        List<String> listOf=authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()); //take role all
        ApiResponse apiResponse=ApiResponse.builder().message("Succesfully checkout").statusCode(HttpStatus.OK.value()).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }
}
