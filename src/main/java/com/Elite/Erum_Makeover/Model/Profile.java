package com.Elite.Erum_Makeover.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    @Id
    private String profileId;

    // Personal Info
    private String fullName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;

    // Address
    private String city;
    private String state;
    private String pinCode;

    // Course Info
    private String courseName;
    private String batchTiming;

    // Experience
    private String priorExperience;
    private String experienceDescription;
    private String skillLevel;

    // Motivation
    private String whyJoin;
    private String careerGoal;
    private String message;

    // Profile Photo Path
    private String profilePhoto;
}