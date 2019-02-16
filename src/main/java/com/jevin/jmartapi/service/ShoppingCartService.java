package com.jevin.jmartapi.service;

import com.jevin.jmartapi.exception.ResourceNotFoundException;
import com.jevin.jmartapi.form.ShoppingCartForm;
import com.jevin.jmartapi.model.ShoppingCartProduct;
import com.jevin.jmartapi.repository.ProductRepo;
import com.jevin.jmartapi.repository.ShoppingCartProductRepo;
import com.jevin.jmartapi.repository.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ShoppingCartRepo shoppingCartRepo;

    @Autowired
    ShoppingCartProductRepo shoppingCartProductRepo;


    public ShoppingCartProduct addProduct(ShoppingCartForm shoppingCartForm) {

        ShoppingCartProduct shoppingCartProduct = new ShoppingCartProduct();

        if (shoppingCartForm.getShoppingCartId() == 0 || shoppingCartForm.getProductId() == 0 || shoppingCartForm.getQuantity() == 0) {
            throw new ResourceNotFoundException("Object not complete");
        }

        shoppingCartProduct.setQuantity(shoppingCartForm.getQuantity());
        shoppingCartProduct.setProduct(productRepo.findById(shoppingCartForm.getProductId()).get());
        shoppingCartProduct.setShoppingCart(shoppingCartRepo.findById(shoppingCartForm.getShoppingCartId()).get());

        return shoppingCartProductRepo.save(shoppingCartProduct);
    }

}



