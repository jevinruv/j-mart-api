package com.jevin.jmartapi.controller;

import com.jevin.jmartapi.model.ShoppingCart;
import com.jevin.jmartapi.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    CheckoutService checkoutService;


    @PostMapping("/{id}")
    public ResponseEntity<?> doCheckOut(@PathVariable int id) {
        ShoppingCart shoppingCart = checkoutService.doCheckOut(id);
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }
}
