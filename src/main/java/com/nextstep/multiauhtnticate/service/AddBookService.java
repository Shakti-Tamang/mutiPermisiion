package com.nextstep.multiauhtnticate.service;

import com.nextstep.multiauhtnticate.Model.AddBook;

import java.util.List;
import java.util.TreeSet;


public interface AddBookService {
    public void addBook(AddBook addBook);
    public void deleteAddedBookById(String id);
    public List<AddBook>listOfAddedBook();
    public void updateBookAdded(String id,AddBook addBook);
}
