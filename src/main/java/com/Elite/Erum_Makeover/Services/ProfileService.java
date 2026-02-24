package com.Elite.Erum_Makeover.Services;

import com.Elite.Erum_Makeover.DTO.ProfileRequest;
import com.Elite.Erum_Makeover.Model.Profile;
import com.Elite.Erum_Makeover.Repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    private static final String UPLOAD_DIR = "uploads/";

    public Profile createProfile(ProfileRequest request) throws IOException {

        String fileName = null;

        if (request.getProfilePhoto() != null && !request.getProfilePhoto().isEmpty()) {

            File folder = new File(UPLOAD_DIR);
            if (!folder.exists()) folder.mkdirs();

            fileName = UUID.randomUUID() + "_" + request.getProfilePhoto().getOriginalFilename();
            File file = new File(UPLOAD_DIR + fileName);
            request.getProfilePhoto().transferTo(file);
        }

        Profile profile = Profile.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .dateOfBirth(LocalDate.parse(request.getDateOfBirth()))
                .gender(request.getGender())
                .city(request.getCity())
                .state(request.getState())
                .pinCode(request.getPinCode())
                .courseName(request.getCourseName())
                .batchTiming(request.getBatchTiming())
                .priorExperience(request.getPriorExperience())
                .experienceDescription(request.getExperienceDescription())
                .skillLevel(request.getSkillLevel())
                .whyJoin(request.getWhyJoin())
                .careerGoal(request.getCareerGoal())
                .message(request.getMessage())
                .profilePhoto(fileName)
                .build();

        return profileRepository.save(profile);
    }
}