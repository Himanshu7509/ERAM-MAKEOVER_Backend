package com.Elite.Erum_Makeover.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "contacts")
@Getter
@Setter
public class Contact
{

    @Id
    private String id;

    private String name;
    private String email;
    private String phone;
    private String message;

    private LocalDateTime createdAt = LocalDateTime.now();
}
