package com.jevin.jmartapi.service;

import com.jevin.jmartapi.configuration.PusherConfig;
import com.jevin.jmartapi.form.ShoppingCartForm;
import com.jevin.jmartapi.model.Product;
import com.jevin.jmartapi.model.ShoppingCart;
import com.jevin.jmartapi.model.ShoppingCartProduct;
import com.jevin.jmartapi.repository.ProductRepo;
import com.jevin.jmartapi.repository.ShoppingCartProductRepo;
import com.jevin.jmartapi.repository.ShoppingCartRepo;
import com.pusher.rest.Pusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ShoppingCartRepo shoppingCartRepo;

    @Autowired
    ShoppingCartProductRepo shoppingCartProductRepo;

    Pusher pusher;

    ShoppingCartProduct shoppingCartProduct = null;

    String CHANNEL_NAME = "cart";
    String event = null;


    @PostConstruct
    public void configure() {
        pusher = PusherConfig.getPusher();
    }

    public ShoppingCartProduct addOrUpdate(ShoppingCartForm shoppingCartForm) {

        Optional<Product> productOptional = productRepo.findById(shoppingCartForm.getProductId());

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            Optional<ShoppingCartProduct> shoppingCartProductOptional = shoppingCartProductRepo.findByProductIdAndAndShoppingCartId(shoppingCartForm.getProductId(), shoppingCartForm.getShoppingCartId());

            if (shoppingCartProductOptional.isPresent()) {
                update(shoppingCartProductOptional, shoppingCartForm);
            } else {

                Optional<ShoppingCart> shoppingCart = shoppingCartRepo.findById(shoppingCartForm.getShoppingCartId());

                if (shoppingCart.isPresent()) {
                    add(product, shoppingCart, shoppingCartForm);
                }
            }

            shoppingCartProduct.getShoppingCart().getShoppingCartProducts().clear();

            pusher.trigger(this.CHANNEL_NAME, this.event, this.shoppingCartProduct);
        }

        return shoppingCartProduct;
    }

    private void add(Product product, Optional<ShoppingCart> shoppingCart, ShoppingCartForm shoppingCartForm) {

        shoppingCartProduct = new ShoppingCartProduct();
        shoppingCartProduct.setQuantity(shoppingCartForm.getQuantity());
        shoppingCartProduct.setShoppingCart(shoppingCart.get());
        shoppingCartProduct.setProduct(product);

        shoppingCartProductRepo.save(shoppingCartProduct);
        event = "itemAdded";
    }

    private void update(Optional<ShoppingCartProduct> shoppingCartProductOptional, ShoppingCartForm shoppingCartForm) {

        shoppingCartProduct = shoppingCartProductOptional.get();
        shoppingCartProduct.setQuantity(shoppingCartForm.getQuantity());

        shoppingCartProductRepo.save(shoppingCartProduct);
        event = "itemUpdated";
    }

}



