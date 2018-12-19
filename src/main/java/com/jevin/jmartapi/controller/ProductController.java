package com.jevin.jmartapi.controller;

import com.jevin.jmartapi.model.Product;
import com.jevin.jmartapi.model.User;
import com.jevin.jmartapi.repository.ProductRepo;
import com.jevin.jmartapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepo repo;

    @GetMapping("/{id}")
    public Optional<Product> get(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<Product> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        return repo.save(product);
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        return repo.save(product);
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
