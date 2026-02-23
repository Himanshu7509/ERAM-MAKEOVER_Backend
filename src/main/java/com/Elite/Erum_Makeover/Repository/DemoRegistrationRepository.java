package com.Elite.Erum_Makeover.Repository;

import com.Elite.Erum_Makeover.Model.DemoRegistration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DemoRegistrationRepository
        extends MongoRepository<DemoRegistration,String> {
}