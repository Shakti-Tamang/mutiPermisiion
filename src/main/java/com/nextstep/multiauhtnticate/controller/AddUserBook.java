package com.nextstep.multiauhtnticate.controller;

import com.nextstep.multiauhtnticate.DTO.SaveBookDto;
import com.nextstep.multiauhtnticate.DTO.UpdateBookDto;
import com.nextstep.multiauhtnticate.Model.AddBook;
import com.nextstep.multiauhtnticate.Response.ApiResponse;
import com.nextstep.multiauhtnticate.service.AddBookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/app/v3")
@Tag(name = "AddBook", description = "API for adding books") // Use @Tag instead of @Api
public class AddUserBook {

    @Autowired
    AddBookService addBookService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addBook")
    @Operation(summary = "Add a new book", description = "Adds a new book to the library")
    public ResponseEntity<ApiResponse>addBook(@Valid @RequestBody SaveBookDto addBook){
        AddBook addBook1=new AddBook();
        addBook1.setBookTitle(addBook.getBookTitle());
        addBook1.setAvailability(addBook.getAvailability());
        addBook1.setBookCategory(addBook.getBookCategory());
        addBook1.setNumberOfBook(addBook.getNumberOfBook());
        addBookService.addBook(addBook1);

        ApiResponse apiResponse=ApiResponse.builder().message("SuccessFullyAdded Book").statusCode(HttpStatus.OK.value()).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('LIBRARIAN')")
    @GetMapping("/getAddedBook")
    @Operation(summary = "Get Added Book",description ="This rout helps to get all added books")
    public ResponseEntity<ApiResponse>getAllBooks(){
        List list=addBookService.listOfAddedBook();
        ApiResponse apiResponse=ApiResponse.<AddBook>builder().message("success").statusCode(HttpStatus.OK.value()).list( list).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse) ;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteById")
    @Operation(summary = "Delete BookById",description = "This rout delete addedBook")
    //if used path varible need send id in path
    public ResponseEntity<ApiResponse>deleteBookById(@RequestParam("id") String id){
        addBookService.deleteAddedBookById(id);
        ApiResponse apiResponse=ApiResponse.builder().message("SuccessfullyDeleted").statusCode(HttpStatus.OK.value()).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/editById")
    @Operation(summary = "Edit By Id",description = "This rout helps to edit by id")
    public ResponseEntity<ApiResponse>editBookById(@RequestParam("id") String id, @RequestBody UpdateBookDto updateBookDto){
        AddBook addBook=new AddBook();
        addBook.setBookTitle(updateBookDto.getBookTitle());
        addBook.setAvailability(updateBookDto.getAvailability());
        addBook.setBookCategory(updateBookDto.getBookCategory());
        addBook.setNumberOfBook(updateBookDto.getNumberOfBook());
        addBookService.updateBookAdded(id,addBook);

        ApiResponse apiResponse=ApiResponse.builder().message("successfully edited").statusCode(HttpStatus.OK.value()).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

  @GetMapping("/getPegination")
  @Operation(summary = "get no.of pegination",description = "this rout provide all no.of pegination")
    public ResponseEntity<ApiResponse>getAllPegination(@RequestParam("bookTitle") String bookTitle,@RequestParam("size") int size,@RequestParam("number") int number){
   Page<AddBook> list=addBookService.getPeginatedProducts(bookTitle, number,size);

        ApiResponse apiResponse=ApiResponse.<AddBook>builder().message("success").statusCode(HttpStatus.OK.value()).pageList(list).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
  }

}

