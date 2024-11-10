package com.nextstep.multiauhtnticate.Repository;

import com.nextstep.multiauhtnticate.Model.BookCheckout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCheckoutRepo extends JpaRepository<BookCheckout,String> {
}
