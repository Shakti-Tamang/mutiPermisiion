package com.nextstep.multiauhtnticate.controller;

import com.nextstep.multiauhtnticate.service.FineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/app/v3")
@Tag(name = "Fine",description = "Taking fine with dtudent who dont return the book even after deu date")
public class FineController {

    @Autowired
    FineService fineService;

    

}
