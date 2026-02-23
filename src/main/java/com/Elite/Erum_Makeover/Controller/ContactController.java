package com.Elite.Erum_Makeover.Controller;

import com.Elite.Erum_Makeover.Model.Contact;
import com.Elite.Erum_Makeover.Services.ContactServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController
{
    private final ContactServices service;

    // ✅ USER → submit form (public)
    @PostMapping
    public Contact submit(@RequestBody Contact msg){
        return service.submit(msg);
    }

    // ✅ ADMIN → view all
    @GetMapping("/getContacts")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Contact> all(){
        return service.getAll();
    }

    // ✅ ADMIN → delete message
    @GetMapping("/getContacts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String GetById(@PathVariable String id){
        service.delete(id);
        return "Message deleted successfully";
    }
    // ✅ ADMIN → delete message
    @DeleteMapping("/getContacts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable String id){
        service.delete(id);
        return "Message deleted successfully";
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Contact> searchByName(@RequestParam String name){
        return service.getByName(name);
    }
}
