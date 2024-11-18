package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.Model.BookCheckout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookCheckoutRepo extends JpaRepository<BookCheckout ,String> {
    List<BookCheckout> findByCheckoutDateBeforeAndDueDateFalse(LocalDateTime now);
}
