package com.nextstep.multiauhtnticate.service;

import com.nextstep.multiauhtnticate.Model.FineModel;
import com.nextstep.multiauhtnticate.Model.UserModel;
import com.nextstep.multiauhtnticate.Repository.FineRepo;
import com.nextstep.multiauhtnticate.Repository.UserRepository;
import com.nextstep.multiauhtnticate.utils.StringUtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FineSevcieImpl implements FineService {

    @Autowired
    FineRepo fineRepo;

    @Autowired
    UserRepository userRepository;

    @Transactional
    @Override
    public void saveFine(FineModel fineModel) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        if(fineModel.getId()==null || fineModel.getId().isEmpty()){

            String hahId= StringUtills.generateRandomAlphaNumeric(6);
            fineModel.setId(hahId);
        }
        fineModel.getUserFine().setFineUser(fineModel);

    }
}
