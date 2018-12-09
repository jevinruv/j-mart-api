package com.jevin.jmartapi.repository;

import com.jevin.jmartapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource(collectionResourceRel = "product", path = "product")
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
