package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.DTO.ProjectionBookDto;
import com.nextstep.multiauhtnticate.Model.AddBook;
import com.nextstep.multiauhtnticate.Model.Courses;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Projection.AddBookProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;


//containing for saerch query
@Repository
public interface BookRepo extends JpaRepository<AddBook, String> {
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


//    inteface approach for jpql:
@Query("SELECT b.bookTitle AS bookTitle, b.bookCategory AS bookCategory, b.numberOfBook AS numberOfBook FROM AddBook b")  @QueryHints({
        @QueryHint(name = "org.hibernate.readOnly", value = "true"),
        @QueryHint(name = "org.hibernate.fetchSize", value = "50"),
        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
        @QueryHint(name = "javax.persistence.cache.retrieveMode", value = "USE"),
        @QueryHint(name = "javax.persistence.cache.storeMode", value = "USE"),
        @QueryHint(name = "javax.persistence.query.timeout", value = "2000")
})

List<AddBookProjection> getAddBookWithRequiredAttribute();

//    native
//    @Query(value = "SELECT book_title AS bookTitle, book_category AS bookCategory, number_of_book AS numberOfBook FROM add_book", nativeQuery = true)
//    List<AddBookProjection> getAddBookWithRequiredAttribute();


    //    By suing jpql
    @Query("SELECT new com.nextstep.multiauhtnticate.DTO.ProjectionBookDto(b.bookTitle, b.bookCategory, b.numberOfBook) FROM AddBook b")
    @QueryHints({
            @QueryHint(name = "org.hibernate.readOnly", value = "true"),
            @QueryHint(name = "org.hibernate.fetchSize", value = "50"),
            @QueryHint(name = "org.hibernate.cacheable", value = "true"),
            @QueryHint(name = "javax.persistence.cache.retrieveMode", value = "USE"),
            @QueryHint(name = "javax.persistence.cache.storeMode", value = "USE"),
            @QueryHint(name = "javax.persistence.query.timeout", value = "2000")
    })
    List<ProjectionBookDto> getAddBookWithRequiredAttributes();


}
