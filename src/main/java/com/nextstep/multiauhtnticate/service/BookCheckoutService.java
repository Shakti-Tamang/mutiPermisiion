package com.nextstep.multiauhtnticate.service;

import com.nextstep.multiauhtnticate.Model.BookCheckout;

public interface BookCheckoutService {
    public void saveCheckout(BookCheckout bookCheckout,String UserId,String BookId);
}
