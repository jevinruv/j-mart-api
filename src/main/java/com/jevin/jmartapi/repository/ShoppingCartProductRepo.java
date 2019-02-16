package com.jevin.jmartapi.repository;

import com.jevin.jmartapi.model.ShoppingCartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartProductRepo extends JpaRepository<ShoppingCartProduct, Integer> {
}
