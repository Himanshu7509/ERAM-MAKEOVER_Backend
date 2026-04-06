package com.Elite.Erum_Makeover.DTO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProfileResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;

    private String fullName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;

    private String city;
    private String state;
    private String pinCode;

    private String courseName;
    private String batchTiming;

    private String priorExperience;
    private String experienceDescription;
    private String skillLevel;

    private String whyJoin;
    private String careerGoal;
    private String message;

    private String imageUrl;   // 🔥 Added Image URL

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
