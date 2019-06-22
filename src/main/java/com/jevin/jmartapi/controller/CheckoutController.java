package com.jevin.jmartapi.controller;

import com.jevin.jmartapi.model.Product;
import com.jevin.jmartapi.model.ShoppingCart;
import com.jevin.jmartapi.model.ShoppingCartProduct;
import com.jevin.jmartapi.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    CheckoutService checkoutService;


    @GetMapping("/{id}")
    public ResponseEntity<?> doCheckOut(@PathVariable int id) {
        ShoppingCart shoppingCart = checkoutService.doCheckOut(id);
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<?> getPurchaseHistory(@PathVariable int userId) {
        List<Product> productList = checkoutService.getPurchaseHistory(userId);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/historyW/{userId}")
    public ResponseEntity<?> getPurchaseHistoryW(@PathVariable int userId) {
        List<ShoppingCartProduct> shoppingCartProductList = checkoutService.getPurchaseHistoryW(userId);
        return new ResponseEntity<>(shoppingCartProductList, HttpStatus.OK);
    }

}
