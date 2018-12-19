package com.jevin.jmartapi.controller;

import com.jevin.jmartapi.model.Discount;
import com.jevin.jmartapi.repository.DiscountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

    @Autowired
    DiscountRepo repo;

    @GetMapping
    public List<Discount> getAll(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Discount> get(@PathVariable int id){
        return repo.findById(id);
    }

    @PostMapping
    public Discount add(@RequestBody Discount discount){
        return repo.save(discount);
    }

}
