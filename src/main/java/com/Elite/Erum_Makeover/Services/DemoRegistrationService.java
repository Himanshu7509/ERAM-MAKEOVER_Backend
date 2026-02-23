package com.Elite.Erum_Makeover.Services;


import com.Elite.Erum_Makeover.Model.DemoRegistration;
import com.Elite.Erum_Makeover.Repository.DemoRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DemoRegistrationService {

    private final DemoRegistrationRepository repo;

    // USER REGISTER
    public DemoRegistration register(DemoRegistration demo){
        return repo.save(demo);
    }

    // ADMIN VIEW ALL
    public List<DemoRegistration> getAll(){
        return repo.findAll();
    }
}