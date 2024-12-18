package com.nextstep.multiauhtnticate.service;

import com.nextstep.multiauhtnticate.DTO.ProjectionBookDto;
import com.nextstep.multiauhtnticate.DTO.SaveBookDto;
import com.nextstep.multiauhtnticate.DTO.UpdateBookDto;
import com.nextstep.multiauhtnticate.Model.AddBook;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Projection.AddBookProjection;
import com.nextstep.multiauhtnticate.Repository.BookRepo;
import com.nextstep.multiauhtnticate.Repository.UserRepository;
import com.nextstep.multiauhtnticate.utils.StringUtills;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AddBookServiceImpl implements AddBookService {


    //    @Lazy //it can be used for notification which is not needed for every operation
    @Autowired
    BookRepo bookRepo;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;




    private Logger logger = LoggerFactory.getLogger(AddBookServiceImpl.class);

//    @Lookup
//    public AddBook getAddBookInstance() // Lookup for prototype-scoped bean
//    {
//        return null;
//    }
//In your case, @Scope("prototype") is not needed because the AddBook objects are tied to a
//    persistent store (database) and don't require a fresh instance for each method call, as
//    the service and repository manage them efficiently.





//    in summary, the @CacheEvict is ensuring that when a new book is added through the addBook
//        method, any cached values related to the addBookCache are cleared so the cache will be
//    updated with the new state when queried again.
   @CacheEvict(value = "addBookCache", allEntries = true)
    @Transactional
    @Override
    public void addBook(SaveBookDto addBookDto) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            UserModel loggedInUser = userRepository.findByUsername(username);
            if (loggedInUser == null) {
                throw new UsernameNotFoundException("Logged-in user not found");
            }

            AddBook addBook = new AddBook();

            modelMapper.map(addBookDto, addBook);
            addBook.setUserToAddBook(loggedInUser);

            if (addBook.getId() == null || addBook.getId().isEmpty()) {
                addBook.setId(StringUtills.generateRandomAlphaNumeric(10));
                logger.info("Generated new ID for book: {}", addBook.getId());
            }

            if (loggedInUser.getListOfBook() == null) {
                loggedInUser.setListOfBook(new ArrayList<>());
            }

            loggedInUser.getListOfBook().add(addBook);

            userRepository.save(loggedInUser);
            bookRepo.save(addBook);

        } catch (Exception ex) {
            logger.error("Error adding book: ", ex);
            throw new RuntimeException("Failed to add book: " + ex.getMessage());
        }
    }



    @CacheEvict(value = "addBookCache", key = "#id")
    @Override
    public void deleteAddedBookById(String id) {
        bookRepo.deleteById(id);
    }




    @Cacheable(value = "addBookCache", key = "#pageNumber + '-' + #pageSize + '-' + #bookTitle")
    @Override
    public List<SaveBookDto> listOfAddedBook(Integer pageNumber, Integer pageSize, String bookTitle) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<AddBook> listPage;

        //trim is especially used when we need to ignore unnecessary white space:
        if (bookTitle != null && !bookTitle.trim().isEmpty()) {
            listPage = bookRepo.findByBookTitleContainingIgnoreCase(bookTitle, pageable);
        } else {
            listPage = bookRepo.findAll(pageable);
        }

        List<AddBook> books = listPage.getContent();
        return books.stream()
                .map(book -> modelMapper.map(book, SaveBookDto.class))
                .collect(Collectors.toList());
    }


    @CachePut(value = "addBookCache", key = "#id")
    @Override
    public void updateBookAdded(String id, UpdateBookDto updateAddBook) {
        AddBook addBook1 = modelMapper.map(updateAddBook, AddBook.class);
        Optional<AddBook> addBookOptional = bookRepo.findById(id);

        if (addBookOptional.isPresent()) {
            AddBook existingBook = addBookOptional.get();

            // Only update fields that are non-null in the provided addBook object
            if (addBook1.getNumberOfBook() != null) {
                existingBook.setNumberOfBook(addBook1.getNumberOfBook());
            }
            if (addBook1.getBookCategory() != null) {
                existingBook.setBookCategory(addBook1.getBookCategory());
            }
            if (addBook1.getAvailability() != null) {
                existingBook.setAvailability(addBook1.getAvailability());
            }
            if (addBook1.getBookTitle() != null) {
                existingBook.setBookTitle(addBook1.getBookTitle());
            }

            bookRepo.save(existingBook);
        }
    }
    @Override
    public List<ProjectionBookDto> getProductWithThreefield() {

        List<AddBookProjection> list = bookRepo.getAddBookWithRequiredAttribute();
        for (AddBookProjection li : list) {
            li.getNumberOfBook();
            li.getBookTitle();
            li.getBookCategory();
        }


        List<ProjectionBookDto> list1 = new ArrayList<>();

        for (AddBookProjection addBookProjection : list) {

            list1.add(new ProjectionBookDto(addBookProjection.getBookCategory(), addBookProjection.getBookTitle(), addBookProjection.getNumberOfBook()));

        }
        return list1;

    }




//using jpql

    //    JPQL (Java Persistence Query Language)

    //it is for easiest last one in custome methos finder

    public List<ProjectionBookDto> getProductWithThreefieldWithNoInterFce() {


        return bookRepo.getAddBookWithRequiredAttributes();
    }


//    @Override
//    public Page<AddBook> getPeginatedProducts(String searchTerm, int page, int size) {
//        Pageable pageable=PageRequest.of(page,size);
//        return  bookRepo.findByBookTitleContainingIgnoreCase(searchTerm,pageable);
//    }



}
