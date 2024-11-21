package com.nextstep.multiauhtnticate.service;
import com.nextstep.multiauhtnticate.DTO.SaveBookDto;
import com.nextstep.multiauhtnticate.DTO.UpdateBookDto;
import com.nextstep.multiauhtnticate.Model.AddBook;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Repository.BookRepo;
import com.nextstep.multiauhtnticate.Repository.UserRepository;
import com.nextstep.multiauhtnticate.utils.StringUtills;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
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
public  class AddBookServiceImpl implements AddBookService {

    @Autowired
    BookRepo bookRepo;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    private Logger logger= LoggerFactory.getLogger(AddBookServiceImpl.class);

    @Lookup
    public AddBook getAddBookInstance() // Lookup for prototype-scoped bean
    {
        return null;
    }

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

            AddBook addBook = getAddBookInstance();  // Get a fresh AddBook instance

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

    @Override
    public void deleteAddedBookById(String id) {
        bookRepo.deleteById(id);
    }

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


    @Override
    public void updateBookAdded(String id, UpdateBookDto updateAddBook) {
        AddBook addBook1=modelMapper.map(updateAddBook,AddBook.class);
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


//    @Override
//    public Page<AddBook> getPeginatedProducts(String searchTerm, int page, int size) {
//        Pageable pageable=PageRequest.of(page,size);
//        return  bookRepo.findByBookTitleContainingIgnoreCase(searchTerm,pageable);
//    }

}
