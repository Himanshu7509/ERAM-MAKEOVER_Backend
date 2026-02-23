package com.Elite.Erum_Makeover.Services;

import com.Elite.Erum_Makeover.Model.Contact;
import com.Elite.Erum_Makeover.Repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServices {

    private final ContactRepository repo;

    // USER → submit form
    public Contact submit(Contact msg) {
        return repo.save(msg);
    }

    // ADMIN → view all messages
    public List<Contact> getAll() {
        return repo.findAll();
    }

    // ADMIN → delete message
    public void delete(String id) {
        repo.deleteById(id);
    }

    public List<Contact> getByName(String name){
        return repo.findByNameContainingIgnoreCase(name);
    }
}