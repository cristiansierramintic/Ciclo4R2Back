package com.ciclo4.reto2.repository.crud;

import com.ciclo4.reto2.model.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCrudRepository extends MongoRepository<User, Integer>{
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByNameOrEmail(String name, String email);
}
