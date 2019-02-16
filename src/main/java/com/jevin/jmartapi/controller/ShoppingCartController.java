package com.jevin.jmartapi.controller;

import com.jevin.jmartapi.form.ShoppingCartForm;
import com.jevin.jmartapi.model.ShoppingCart;
import com.jevin.jmartapi.model.ShoppingCartProduct;
import com.jevin.jmartapi.repository.ShoppingCartRepo;
import com.jevin.jmartapi.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/carts")
public class ShoppingCartController {

    @Autowired
    ShoppingCartRepo repo;

    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/{id}")
    public Optional<ShoppingCart> get(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<ShoppingCart> getAll() {
        return repo.findAll();
    }

    @PostMapping("/create")
    public ShoppingCart createCart(@RequestBody int userId) {
        return repo.save(new ShoppingCart(userId));
    }

    @PostMapping
    public ShoppingCartProduct add(@RequestBody ShoppingCartForm shoppingCartForm) {
        return shoppingCartService.addProduct(shoppingCartForm);
    }


    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {

        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }


}
