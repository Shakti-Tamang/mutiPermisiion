package com.nextstep.multiauhtnticate.service;

import com.nextstep.multiauhtnticate.Model.AddBook;
import com.nextstep.multiauhtnticate.Model.BookCheckout;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Repository.BookCheckoutRepo;
import com.nextstep.multiauhtnticate.Repository.BookRepo;
import com.nextstep.multiauhtnticate.Repository.UserRepository;
import com.nextstep.multiauhtnticate.utils.StringUtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookCheckoutImpl implements BookCheckoutService {
    @Autowired
    BookCheckoutRepo bookCheckoutRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepo bookRepo;

//
//    All operations within the method are treated as a single unit of work. If any
//    operation fails (e.g., if saving the user or the book fails), the entire
//    transaction is rolled back, and none of the changes are committed to the
//    database. This prevents partial updates that could lead to inconsistencies.
    @Transactional
    @Override
    public void saveCheckout(BookCheckout bookCheckout, String UserId, String BookId) {
        UserModel loggedInUser = userRepository.findById(UserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        AddBook book = bookRepo.findById(BookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));


        bookCheckout.setUsersBook(loggedInUser);
        bookCheckout.setAddBookCheckout(book);


        if (loggedInUser.getListOfBookCheckout() == null) {
            loggedInUser.setListOfBookCheckout(new ArrayList<>());
        }
        if (book.getListOfCheckoutBook() == null) {
            book.setListOfCheckoutBook(new ArrayList<>());
        }

            loggedInUser.getListOfBookCheckout().add(bookCheckout);
            book.getListOfCheckoutBook().add(bookCheckout);


            int quantity = book.getNumberOfBook();
            if (quantity <= 0) {
                throw new RuntimeException("No copies of the book available for checkout");
            }
            book.setNumberOfBook(quantity - 1);



            if (bookCheckout.getId() == null || bookCheckout.getId().isEmpty()) {
                String hashId = StringUtills.generateRandomAlphaNumeric(7);
                bookCheckout.setId(hashId);
            }


        userRepository.save(loggedInUser);
        bookRepo.save(book);
        bookCheckoutRepo.save(bookCheckout);

    }

    @Override
    public void deleteByCode(String id) {
        bookCheckoutRepo.deleteById(id);
    }

    @Override
    public BookCheckout getById(String id) {
       Optional<BookCheckout> bookCheckoutOne=bookCheckoutRepo.findById(id);
       return bookCheckoutOne.orElse(null);

    }

    @Override
    public List<BookCheckout> listOfBookCheckout() {

        List<BookCheckout>list=bookCheckoutRepo.findAll();
        return null;
    }

}
