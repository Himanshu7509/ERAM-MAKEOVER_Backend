package com.Elite.Erum_Makeover.Repository;

import com.Elite.Erum_Makeover.Model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile, String> {
}