package com.nextstep.multiauhtnticate.service;

import com.nextstep.multiauhtnticate.Model.BookCheckout;
import com.nextstep.multiauhtnticate.Model.FineModel;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Repository.BookCheckoutRepo;
import com.nextstep.multiauhtnticate.Repository.FineRepo;
import com.nextstep.multiauhtnticate.Repository.UserRepository;
import com.nextstep.multiauhtnticate.utils.StringUtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class FineSevcieImpl implements FineService {

    @Autowired
    FineRepo fineRepo;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookCheckoutRepo bookCheckoutRepo;

    @Transactional
    @Override
    public void saveFine(FineModel fineModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //prsent Time
        LocalDateTime now = LocalDateTime.now();

        List<BookCheckout> list = bookCheckoutRepo.findByCheckoutDateBeforeAndDueDateFalse(now);
        for (BookCheckout li : list) {
            if (fineModel.getId() == null || fineModel.getId().isEmpty()) {
                String hahId = StringUtills.generateRandomAlphaNumeric(6);
                //find difference between due date and present datye:
                  long lateToSubmitDate= ChronoUnit.DAYS.between(li.getDueDate(),now);

                  if(lateToSubmitDate<0) {
                     short amount=fineModel.getFine();
                     if(amount>0){
                         fineModel.setFine((short) (amount+10));
                     }

                      fineModel.setId(hahId);
                  }
            }
        }
        fineModel.getUserFine().setFineUser(fineModel);
    }
}
