package com.Elite.Erum_Makeover.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "demo_registrations")
@Getter
@Setter
public class DemoRegistration
{

    @Id
    private String id;

    private String userId;      // from logged in user
    private String name;
    private String email;
    private String phone;

    private LocalDateTime registeredAt = LocalDateTime.now();
}
