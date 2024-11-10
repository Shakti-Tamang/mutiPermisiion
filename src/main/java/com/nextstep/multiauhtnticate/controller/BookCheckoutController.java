package com.nextstep.multiauhtnticate.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Valid
@RequestMapping("/app/v4")
@Tag(name = "CheckoutBook",description = "This Api is Used to Checkout Book")
public class BookCheckoutController {
}
