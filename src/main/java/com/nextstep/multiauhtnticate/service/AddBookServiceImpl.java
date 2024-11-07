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

import java.util.ArrayList;

@Service
public class AddBookServiceImpl implements AddBookService {

    @Autowired
    BookRepo bookRepo;
    @Autowired
    UserRepository userRepository;

    @Override
    public void addBook(AddBook addBook) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        UserModel loggedInUser=userRepository.findByUsername(authentication.getName());

        if(loggedInUser !=null){
          addBook.setUserToAddBook(loggedInUser);

          if(loggedInUser.getListOfBook()==null){
              loggedInUser.setListOfBook(new ArrayList<>());
          }
          loggedInUser.getListOfBook().add(addBook);
            if(addBook.getId()==null || addBook.getId().isEmpty()){
                String hashId= StringUtills.generateRandomAlphaNumeric(10);
                addBook.setId(hashId);
            }
       userRepository.save(loggedInUser);
            bookRepo.save(addBook);

        }



    }
}
