package com.jevin.jmartapi.controller;

import com.jevin.jmartapi.model.Product;
import com.jevin.jmartapi.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepo productRepo;

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable int id) {
        return productRepo.findById(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepo.save(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productRepo.save(product);
    }

    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable int id) {

        boolean flag = false;

        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            flag = true;
        }
        return flag;
    }
}
