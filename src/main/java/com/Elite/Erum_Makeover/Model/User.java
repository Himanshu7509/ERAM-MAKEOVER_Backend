package com.Elite.Erum_Makeover.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Setter
@NoArgsConstructor      // ⭐ REQUIRED FOR JSON
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String fullName;
    private String email;
    private String password;

}