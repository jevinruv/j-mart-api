package com.jevin.jmartapi.repository;

import com.jevin.jmartapi.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Integer> {
    Optional<ShoppingCart> findByUserId(int id);
    Optional<ShoppingCart> findByUserIdAndStatus(int userId, String status);
    Optional<List<ShoppingCart>> findAllByUserIdAndStatus(int userId, String status);
}
