package com.jevin.jmartapi.repository;

import com.jevin.jmartapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepo extends JpaRepository<User, Integer> {
}
