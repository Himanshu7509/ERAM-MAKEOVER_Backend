package com.Elite.Erum_Makeover.Repository;

import com.Elite.Erum_Makeover.Model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin,String> {
    Optional<Admin> findByEmail(String email);
}
