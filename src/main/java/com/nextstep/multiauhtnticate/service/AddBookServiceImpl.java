package com.nextstep.multiauhtnticate.service;


import com.nextstep.multiauhtnticate.Model.AddBook;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Repository.BookRepo;
import com.nextstep.multiauhtnticate.Repository.UserRepository;
import com.nextstep.multiauhtnticate.utils.StringUtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddBookServiceImpl implements AddBookService {

    @Autowired
    BookRepo bookRepo;
    @Autowired
    UserRepository userRepository;

    @Override
    public void addBook(AddBook addBook) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            UserModel loggedInUser = userRepository.findByUsername(authentication.getName());

            if (loggedInUser != null) {
                addBook.setUserToAddBook(loggedInUser);

                if (loggedInUser.getListOfBook() == null) {
                    loggedInUser.setListOfBook(new ArrayList<>());
                }
                loggedInUser.getListOfBook().add(addBook);
                if (addBook.getId() == null || addBook.getId().isEmpty()) {
                    String hashId = StringUtills.generateRandomAlphaNumeric(10);
                    addBook.setId(hashId);
                }
                userRepository.save(loggedInUser);
                bookRepo.save(addBook);

            }
        }
        catch (Exception ex){
         ex.printStackTrace();
         throw  new RuntimeException("failed to add book"+ex.getMessage());
        }
    }

    @Override
    public void deleteAddedBookById(String id) {
        bookRepo.deleteById(id);
    }

    @Override
    public List<AddBook> listOfAddedBook() {

        List<AddBook>list=  bookRepo.findAll();

        return list.isEmpty()?new ArrayList<>():list;
    }

    @Override
    public void updateBookAdded(String id, AddBook addBook) {
        Optional<AddBook> addBook1=bookRepo.findById(id);

        if(addBook1.isPresent()){
            AddBook addBook2=addBook1.get();

            addBook2.setBootQuantity(addBook.getBootQuantity());
            addBook2.setBookCategory(addBook.getBookCategory());
            addBook2.setAvailability(addBook.getAvailability());
            addBook2.setBookTitle(addBook.getBookTitle());
            bookRepo.save(addBook2);
        }
    }
}
