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
    private final S3Service s3Service;   // 👈 inject here

    public Profile createProfile(ProfileRequest request) {

        String imageUrl = null;

        if (request.getProfilePhoto() != null &&
                !request.getProfilePhoto().isEmpty()) {

            imageUrl = s3Service.uploadFile(
                    request.getProfilePhoto(),
                    "profileImages"
            );
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
                .profilePhoto(imageUrl)   // ✅ save S3 URL
                .build();

        return profileRepository.save(profile);
    }
}
