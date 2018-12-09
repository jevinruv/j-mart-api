package com.jevin.jmartapi.repository;

import com.jevin.jmartapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "category", path = "category")
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
