package com.Elite.Erum_Makeover.Repository;


import com.Elite.Erum_Makeover.Model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContactRepository extends MongoRepository<Contact, String>
{
    List<Contact> findByNameContainingIgnoreCase(String name);
}