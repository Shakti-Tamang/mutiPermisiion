package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.Model.AddBook;
import com.nextstep.multiauhtnticate.Model.Courses;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Projection.AddBookProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


//containing for saerch query
@Repository
public interface BookRepo extends JpaRepository<AddBook,String> {
    //it is difficult approach  if

//    user so passmodel
    public List<AddBook> findByUserToAddBook(UserModel userModel);


//
//
//    In simple terms, Pageable and the Page interface in Spring Data JPA help you manage and
//        control large sets of data by breaking them down into smaller,
//        manageable "pages."
//
//    Why Pageable is Used
//    When dealing with large databases, retrieving all records at once can be slow and
//    consume a lot of memory. Pagination solves this by letting you fetch a specific
//        "page" of data instead of everything. For example, if you have 1,000 products but
//    want to display only 10 per page, you can use Pageable to fetch the data page-by-page.
//
//    How Pageable Works
//    Define Pageable in Your Query: In your repository interface, when you add Pageable as a
//    parameter to a query method, it allows that query to take pagination instructions
//            (such as page number and size).
//
//    Specify Page Information: When calling the method, you create a Pageable
//    object (usually with PageRequest.of(pageNumber, pageSize)) to tell the database which
//    page to retrieve and how many items should be on each page.
//
//    Receive Results as a Page: The method returns a Page object containing only the data for
//    the requested page, along with useful information like the total number of pages, the
//    total number of items, and whether there’s a next or previous page.


    //patial match containing used LIKE operator   pegable domain wala  it ignores case
    public Page<AddBook> findByBookTitleContainingIgnoreCase(String bookTitle, Pageable pageable);

    @Query("SELECT b FROM AddBook b WHERE b.id = :id")
    public Optional<AddBook> findByIdExists(@Param("id") String id);


    @Query(value = "select book_category,book_title,number_of_book from add_book",nativeQuery = true)
    List<AddBookProjection> getAddBookWithRequiredAttribute();


}
