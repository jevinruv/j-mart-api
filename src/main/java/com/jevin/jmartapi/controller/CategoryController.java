package com.jevin.jmartapi.controller;

import com.jevin.jmartapi.exception.ResourceNotFoundException;
import com.jevin.jmartapi.model.Category;
import com.jevin.jmartapi.model.Product;
import com.jevin.jmartapi.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryRepo repo;

    @GetMapping("/{id}")
    public Optional<Category> get(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<Category> getAll() {
        return repo.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addOrUpdate(@RequestBody Category category) {
        Category savedCategory = repo.save(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return repo.findById(id).map(category -> {
            repo.delete(category);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Category Id " + id + " not found"));
    }

}
