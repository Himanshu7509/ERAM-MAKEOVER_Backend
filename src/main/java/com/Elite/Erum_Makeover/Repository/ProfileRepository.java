package com.Elite.Erum_Makeover.Repository;

import com.Elite.Erum_Makeover.Model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfileRepository extends MongoRepository<Profile, String>
{
    Optional<Profile> findByUserId(String userId);
}