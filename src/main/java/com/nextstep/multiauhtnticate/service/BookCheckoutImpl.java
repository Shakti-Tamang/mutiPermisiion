package com.nextstep.multiauhtnticate.service;

import com.nextstep.multiauhtnticate.Model.AddBook;
import com.nextstep.multiauhtnticate.Model.BookCheckout;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Repository.BookCheckoutRepo;
import com.nextstep.multiauhtnticate.Repository.BookRepo;
import com.nextstep.multiauhtnticate.Repository.UserRepository;
import com.nextstep.multiauhtnticate.utils.StringUtills;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookCheckoutImpl implements BookCheckoutService {
    @Autowired
    BookCheckoutRepo bookCheckoutRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepo bookRepo;

    @Override
    public void saveCheckout(BookCheckout bookCheckout,String id) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserModel loggedInUser=userRepository.findByUsername(authentication.getName());

//        List<AddBook>list=bookRepo.findByUserToAddBook(loggedInUser);
        AddBook book=bookRepo.findByIdExists(id);


        if(loggedInUser !=null && book!=null){

            bookCheckout.setUsersBook(loggedInUser);
            bookCheckout.setAddBookCheckout(book);

            if(loggedInUser.getListOfBookCheckout()==null && book.getListOfCheckoutBook()==null){

                loggedInUser.setListOfBook(new ArrayList<>());
                book.setListOfCheckoutBook(new ArrayList<>());
            }
            loggedInUser.getListOfBookCheckout().add(bookCheckout);
            book.getListOfCheckoutBook().add(bookCheckout);
            userRepository.save(loggedInUser);

            int quantity=book.getNumberOfBook();
            quantity--;
            if (quantity > 0) {
                quantity--;
                book.setNumberOfBook(quantity);
                bookRepo.save(book);
            } else {
                throw new RuntimeException("No copies of the book available for checkout");
            }
            bookRepo.save(book);
            if(bookCheckout.getId()==null || bookCheckout.getId().isEmpty()){
                String hashId= StringUtills.generateRandomAlphaNumeric(7);
                bookCheckout.setId(hashId);
            }

            bookCheckoutRepo.save(bookCheckout);
        }

//        The purpose of the stream line in the above code is to locate a specific
//        book in the list of books (retrieved for the logged-in user) that matches
//        the ID of the book to be checked out. Here’s a breakdown of what each part
//    of this line does:

//        AddBook selectedBook = list.stream()
//                .filter(book -> book.getId().equals(bookCheckout.getAddBookCheckout().getId()))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Selected book not found"));
    }
}
