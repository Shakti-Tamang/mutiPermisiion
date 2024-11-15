package com.nextstep.multiauhtnticate.service;

import com.nextstep.multiauhtnticate.Model.BookCheckout;

import java.util.List;

public interface BookCheckoutService {
    public void saveCheckout(BookCheckout bookCheckout,String UserId,String BookId);
    public void deleteByCode(String id);
    public BookCheckout getById(String id);
    public List<BookCheckout>listOfBookCheckout();
}
