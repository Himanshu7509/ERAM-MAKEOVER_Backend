package com.Elite.Erum_Makeover.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="enrollments")
@Getter
@Setter
public class Enrollment {


        @Id
        private String Enrollmeentid;

        private String userId;
        private String fullName;
        private String email;

        private String courseId;

        private String phone;
        private String message;

        private LocalDateTime enrolledAt;
}