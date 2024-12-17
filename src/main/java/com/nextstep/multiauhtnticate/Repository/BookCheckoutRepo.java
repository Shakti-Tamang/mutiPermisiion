package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.Model.BookCheckout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookCheckoutRepo extends JpaRepository<BookCheckout, String> {
//    The keyword Before in a Spring Data JPA query method translates to a less than (<) condition in the underlying SQL query.
//    It means that the dueDate field in your entity will be compared to the now value (a LocalDateTime).
//


    // Fetch all records where dueDate is before the current time
    List<BookCheckout> findByDueDateBefore(LocalDateTime now);

//    Explanation:
//    SELECT bc: Fetches records from the BookCheckout table (aliased as bc).
//    JOIN FETCH bc.addBookCheckout: Joins and fetches related AddBook entities (ab).
//    JOIN FETCH ab.userToAddBook: Further joins and fetches related UserModel entities (u).
//    WHERE Clause: Filters records based on the faculty field in the UserModel table.
//    ORDER BY bc.checkoutDate: Sorts the results by the checkoutDate field in ascending order (default behavior).
//    Result: Returns a list of BookCheckout records, with associated AddBook and UserModel entities, sorted by the checkoutDate.
    @Query("SELECT bc FROM BookCheckout bc " + "JOIN FETCH bc.addBookCheckout ab " + "JOIN FETCH ab.userToAddBook u " + "WHERE u.faculty = :faculty " + "ORDER BY bc.checkoutDate DESC ")
    List<BookCheckout> findByAddBookCheckout_UserToAddBook_FacultyOrderByLatestCheckoutDate(@Param("faculty") String faculty);


}
