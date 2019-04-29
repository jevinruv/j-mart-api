package com.jevin.jmartapi.service;

import com.jevin.jmartapi.exception.ResourceNotFoundException;
import com.jevin.jmartapi.model.ShoppingCart;
import com.jevin.jmartapi.repository.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {

    @Autowired
    ShoppingCartRepo shoppingCartRepo;

    @Autowired
    ShoppingCartService shoppingCartService;


    public ShoppingCart doCheckOut(int id){

        ShoppingCart shoppingCart = updateCartStatus(id);
        int userId = shoppingCart.getUserId();

        ShoppingCart newShoppingCart = shoppingCartService.fetchCart(userId);

        return newShoppingCart;
    }

    public ShoppingCart updateCartStatus(int id) {

        ShoppingCart shoppingCart = shoppingCartRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for this id :: " + id));

        shoppingCart.setStatus("PAYED");
        shoppingCart = shoppingCartRepo.save(shoppingCart);

        return shoppingCart;
    }

}
