package com.jevin.jmartapi.service;

import com.jevin.jmartapi.exception.ResourceNotFoundException;
import com.jevin.jmartapi.model.Product;
import com.jevin.jmartapi.model.ShoppingCart;
import com.jevin.jmartapi.model.ShoppingCartProduct;
import com.jevin.jmartapi.repository.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckoutService {

    @Autowired
    ShoppingCartRepo shoppingCartRepo;

    @Autowired
    ShoppingCartService shoppingCartService;


    public ShoppingCart doCheckOut(int id) {

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

    public List<Product> getPurchaseHistory(int userId) {

        List<ShoppingCart> shoppingCartList = shoppingCartRepo.findAllByUserIdAndStatus(userId, "PAYED")
                .orElseThrow(() -> new ResourceNotFoundException("No Purchase History for this userId :: " + userId));

        List<Product> productList = new ArrayList<>();

        shoppingCartList.forEach(shoppingCart ->
                shoppingCart.getShoppingCartProducts().forEach(shoppingCartProduct -> {
                            productList.add(shoppingCartProduct.getProduct());
                        }
                ));

        return productList;
    }

    public List<ShoppingCartProduct> getPurchaseHistoryW(int userId) {

        List<ShoppingCart> shoppingCartList = shoppingCartRepo.findAllByUserIdAndStatus(userId, "PAYED")
                .orElseThrow(() -> new ResourceNotFoundException("No Purchase History for this userId :: " + userId));

        List<ShoppingCartProduct> shoppingCartProductList = new ArrayList<>();

        shoppingCartList.forEach(shoppingCart ->
                shoppingCart.getShoppingCartProducts().forEach(shoppingCartProduct -> {
                            shoppingCartProductList.add(shoppingCartProduct);
                        }
                ));

        return shoppingCartProductList;
    }

}
