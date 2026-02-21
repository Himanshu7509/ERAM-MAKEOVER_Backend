package com.Elite.Erum_Makeover.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="admins")
@Getter
@Setter
public class Admin {

    @Id
    private String id;

    private String fullName;
    private String email;
    private String password;
}
