package com.nextstep.multiauhtnticate.service;

import com.nextstep.multiauhtnticate.Model.AddBook;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.TreeSet;


public interface AddBookService {
    public void addBook(AddBook addBook);
    public void deleteAddedBookById(String id);
    public List<AddBook>listOfAddedBook();
    public void updateBookAdded(String id,AddBook addBook);
//    The Page<Product> return type is
//    used to make data retrieval more efficient and manageable, especially when dealing with
//    large sets of data. In the method:

    public Page<AddBook>getPeginatedProducts(String searchTerm,int page,int size);
}
