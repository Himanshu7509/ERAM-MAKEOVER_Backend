package com.Elite.Erum_Makeover.Repository;

import com.Elite.Erum_Makeover.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByEmail(String email);

}