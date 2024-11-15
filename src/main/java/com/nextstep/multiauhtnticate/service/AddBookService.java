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
//    the Page and Pageable interfaces are part of the Spring Data JPA library, specifically
//    under the org.springframework.data.domain package.
    //    The Page<Product> return type is
//    used to make data retrieval more efficient and manageable, especially when dealing with
//    large sets of data. In the method:
//    Yes, in Spring Data JPA, Page is an interface provided by the framework to represent
//        a paginated set of results, often returned by methods using pagination. The Page
//        interface contains metadata about the page as well as the actual data retrieved.
//    Here’s an overview of how it works and what it provides:
    public Page<AddBook>getPeginatedProducts(String searchTerm,int page,int size);
}
