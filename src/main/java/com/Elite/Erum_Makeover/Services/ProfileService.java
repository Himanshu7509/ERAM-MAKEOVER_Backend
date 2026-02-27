package com.Elite.Erum_Makeover.Services;
import com.Elite.Erum_Makeover.DTO.ProfileRequest;
import com.Elite.Erum_Makeover.Model.Image;
import com.Elite.Erum_Makeover.Model.Profile;
import com.Elite.Erum_Makeover.Repository.ImageRepository;
import com.Elite.Erum_Makeover.Repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ImageRepository imageRepository;

    // ✅ Create Profile
    public Profile createProfile(ProfileRequest request) {

        Profile profile = Profile.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .dateOfBirth(request.getDateOfBirth())
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
                .profileImageId(request.getProfileImageId())  // 🔥 save imageId
                .build();

        return profileRepository.save(profile);
    }

    // ✅ Get Profile With Image
    public Map<String, Object> getProfile(String id) {

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        Image image = null;

        if (profile.getProfileImageId() != null) {
            image = imageRepository
                    .findById(profile.getProfileImageId())
                    .orElse(null);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("profile", profile);
        response.put("profileImage", image);

        return response;
    }
}
