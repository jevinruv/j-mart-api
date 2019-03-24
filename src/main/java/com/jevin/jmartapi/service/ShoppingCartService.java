package com.jevin.jmartapi.service;

import com.jevin.jmartapi.configuration.PusherConfig;
import com.jevin.jmartapi.exception.ResourceNotFoundException;
import com.jevin.jmartapi.form.ShoppingCartForm;
import com.jevin.jmartapi.model.Product;
import com.jevin.jmartapi.model.ShoppingCart;
import com.jevin.jmartapi.model.ShoppingCartProduct;
import com.jevin.jmartapi.repository.ProductRepo;
import com.jevin.jmartapi.repository.ShoppingCartProductRepo;
import com.jevin.jmartapi.repository.ShoppingCartRepo;
import com.pusher.rest.Pusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    public ResponseEntity<?> addOrUpdate(ShoppingCartForm shoppingCartForm) {

        Product product = productRepo.findById(shoppingCartForm.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + shoppingCartForm.getProductId()));

        ShoppingCart shoppingCart = shoppingCartRepo.findById(shoppingCartForm.getShoppingCartId())
                .orElseThrow(() -> new ResourceNotFoundException("Shopping Cart not found for this id :: " + shoppingCartForm.getShoppingCartId()));


        Optional<ShoppingCartProduct> shoppingCartProductOptional =
                shoppingCartProductRepo.findByProductIdAndAndShoppingCartId(shoppingCartForm.getProductId(), shoppingCartForm.getShoppingCartId());

        if (shoppingCartProductOptional.isPresent()) {
            update(shoppingCartForm, shoppingCartProductOptional);
        } else {
            add(shoppingCartForm, shoppingCart, product);
        }

        this.shoppingCartProduct.makePusherReady();
        pusher.trigger(this.CHANNEL_NAME, this.event, this.shoppingCartProduct);

        return new ResponseEntity<>(this.shoppingCartProduct, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCart(int id) {

        Optional<ShoppingCart> shoppingCart = shoppingCartRepo.findById(id);

        if (!shoppingCart.isPresent()) {
            throw new ResourceNotFoundException("Shopping Cart Id " + id + " not found");
        }

        shoppingCartRepo.deleteById(id);

        this.event = "cartEmptied";
        pusher.trigger(this.CHANNEL_NAME, this.event, "");

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCartItem(ShoppingCartForm shoppingCartForm) {

        Optional<ShoppingCartProduct> productInCart =
                shoppingCartProductRepo.findByProductIdAndAndShoppingCartId(shoppingCartForm.getProductId(), shoppingCartForm.getShoppingCartId());

        if (!productInCart.isPresent()) {
            throw new ResourceNotFoundException("Shopping Cart Id " + shoppingCartForm.getShoppingCartId() + " not found");
        }

        this.shoppingCartProduct = productInCart.get();
        shoppingCartProductRepo.deleteById(this.shoppingCartProduct.getId());

        this.shoppingCartProduct.makePusherReady();
        this.event = "cartEmptied";
        pusher.trigger(this.CHANNEL_NAME, this.event, this.shoppingCartProduct);

        return new ResponseEntity<>(this.shoppingCartProduct, HttpStatus.OK);
    }

    private void add(ShoppingCartForm shoppingCartForm, ShoppingCart shoppingCart, Product product) {

        shoppingCartProduct = new ShoppingCartProduct();
        shoppingCartProduct.setQuantity(shoppingCartForm.getQuantity());
        shoppingCartProduct.setShoppingCart(shoppingCart);
        shoppingCartProduct.setProduct(product);

        shoppingCartProductRepo.save(shoppingCartProduct);
        event = "itemAdded";
    }

    private void update(ShoppingCartForm shoppingCartForm, Optional<ShoppingCartProduct> shoppingCartProductOptional) {

        shoppingCartProduct = shoppingCartProductOptional.get();
        shoppingCartProduct.setQuantity(shoppingCartForm.getQuantity());

        shoppingCartProductRepo.save(shoppingCartProduct);
        event = "itemUpdated";
    }


}



