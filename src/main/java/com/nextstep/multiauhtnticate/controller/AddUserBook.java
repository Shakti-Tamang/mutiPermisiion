package com.nextstep.multiauhtnticate.controller;

import com.nextstep.multiauhtnticate.DTO.ProjectionBookDto;
import com.nextstep.multiauhtnticate.DTO.SaveBookDto;
import com.nextstep.multiauhtnticate.DTO.UpdateBookDto;

import com.nextstep.multiauhtnticate.Response.ApiResponse;

import com.nextstep.multiauhtnticate.service.AddBookService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/app/v3")

@Tag(name = "AddBook", description = "API for adding books") // Use @Tag instead of @Api
public class AddUserBook {

//    filed injection
//
//    @Autowired
//    AddBookService addBookService;
//    @Autowired
//    ModelMapper modelMapper;
//
//
//    private Logger logger = LoggerFactory.getLogger(AddUserBook.class);


//    contructor njection
    private final AddBookService addBookService;

    private final ModelMapper modelMapper;
    private final Logger logger;

    public AddUserBook(AddBookService addBookService,ModelMapper modelMapper){

        this.addBookService=addBookService;
        this.modelMapper=modelMapper;
        this.logger=LoggerFactory.getLogger(AddUserBook.class);

    }
//



    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addBook")
    @Operation(summary = "Add a new book", description = "Adds a new book to the library")
    public ResponseEntity<ApiResponse> addBook(@Valid @RequestBody SaveBookDto addBookDto) {
//
//        logger.error("error");

        addBookService.addBook(addBookDto);

        ApiResponse apiResponse = ApiResponse.builder().message("SuccessFullyAdded Book").statusCode(HttpStatus.OK.value()).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getProjected")
    @Operation(summary = "get limited fields", description = "api to get limited field")
    public ResponseEntity<ApiResponse> getLimted() {
        List<ProjectionBookDto> list = addBookService.getProductWithThreefield();

        ApiResponse apiResponse = ApiResponse.<ProjectionBookDto>builder().message("message").statusCode(HttpStatus.OK.value()).list(list).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('LIBRARIAN')")
    @GetMapping("/getAddedBook")
    @Operation(summary = "Get Added Book", description = "This route helps to get all added books")
    public ResponseEntity<ApiResponse> getAllBooks(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "bookTitle", required = false) String bookTitle) {

        List<SaveBookDto> books = addBookService.listOfAddedBook(pageNumber, pageSize, bookTitle);

        ApiResponse apiResponse = ApiResponse.<SaveBookDto>builder()
                .message("success")
                .statusCode(HttpStatus.OK.value())
                .list(books)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteById")
    @Operation(summary = "Delete BookById", description = "This rout delete addedBook")
    //if used path varible need send id in path
    public ResponseEntity<ApiResponse> deleteBookById(@RequestParam("id") String id) {
        addBookService.deleteAddedBookById(id);
        ApiResponse apiResponse = ApiResponse.builder().message("SuccessfullyDeleted").statusCode(HttpStatus.OK.value()).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/editById")
    @Operation(summary = "Edit By Id", description = "This rout helps to edit by id")
    public ResponseEntity<ApiResponse> editBookById(@RequestParam("id") String id, @RequestBody UpdateBookDto updateBookDto) {
        addBookService.updateBookAdded(id, updateBookDto);

        ApiResponse apiResponse = ApiResponse.builder().message("successfully edited").statusCode(HttpStatus.OK.value()).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

//  @GetMapping("/getPegination")
//  @Operation(summary = "get no.of pegination",description = "this rout provide all no.of pegination")
//    public ResponseEntity<ApiResponse>getAllPegination(@RequestParam("bookTitle") String bookTitle,@RequestParam("size") int size,@RequestParam("number") int number){
//   Page<AddBook> list=addBookService.getPeginatedProducts(bookTitle, number,size);
//
//        ApiResponse apiResponse=ApiResponse.<AddBook>builder().message("success").statusCode(HttpStatus.OK.value()).pageList(list).build();
//        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
//  }

}

