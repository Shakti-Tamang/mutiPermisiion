package com.nextstep.multiauhtnticate.service;
import com.nextstep.multiauhtnticate.DTO.SaveBookDto;
import com.nextstep.multiauhtnticate.DTO.UpdateBookDto;
import com.nextstep.multiauhtnticate.Model.AddBook;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Repository.BookRepo;
import com.nextstep.multiauhtnticate.Repository.UserRepository;
import com.nextstep.multiauhtnticate.utils.StringUtills;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class AddBookServiceImpl implements AddBookService {

    @Autowired
    BookRepo bookRepo;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    @Override
    public void addBook(SaveBookDto addBook) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            AddBook addBook1=modelMapper.map(addBook,AddBook.class);

            UserModel loggedInUser = userRepository.findByUsername(authentication.getName());

            if (loggedInUser != null) {
                addBook1.setUserToAddBook(loggedInUser);

                if (loggedInUser.getListOfBook() == null) {
                    loggedInUser.setListOfBook(new ArrayList<>());
                }
                loggedInUser.getListOfBook().add(addBook1);
                if (addBook1.getId() == null || addBook1.getId().isEmpty()) {
                    String hashId = StringUtills.generateRandomAlphaNumeric(10);
                    addBook1.setId(hashId);
                }
                userRepository.save(loggedInUser);
                bookRepo.save(addBook1);
            }else{
                throw new IllegalArgumentException("logged in userf notfound");

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
    public List<SaveBookDto> listOfAddedBook(Integer pageNumber,Integer pageSize) {

//   //        total number of data
//        int pageSize1=10;
//  //total number of pages
//        int pageNumber1=5;
        Pageable pageable=PageRequest.of(pageNumber,pageSize);
        Page<AddBook>list1=this.bookRepo.findAll(pageable);
       List<AddBook>list=list1.getContent();

        List<SaveBookDto>list2=list.stream().map((post)->this.modelMapper.map(post,SaveBookDto.class)).toList();
        return list2.isEmpty()?new ArrayList<>():list2;
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
