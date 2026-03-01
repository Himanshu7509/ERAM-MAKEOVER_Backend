package com.Elite.Erum_Makeover.Services;
import com.Elite.Erum_Makeover.DTO.ProfileRequest;
import com.Elite.Erum_Makeover.Model.Image;
import com.Elite.Erum_Makeover.Model.Profile;
import com.Elite.Erum_Makeover.Repository.ImageRepository;
import com.Elite.Erum_Makeover.Repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ImageRepository imageRepository;
    // 🔥 Create or Update Profile
    public Profile saveOrUpdateProfile(String userId, Profile request) {

        Profile profile = profileRepository.findByUserId(userId)
                .orElse(new Profile());

        profile.setUserId(userId);
        profile.setFullName(request.getFullName());
        profile.setEmail(request.getEmail());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setDateOfBirth(request.getDateOfBirth());
        profile.setGender(request.getGender());
        profile.setCity(request.getCity());
        profile.setState(request.getState());
        profile.setPinCode(request.getPinCode());
        profile.setCourseName(request.getCourseName());
        profile.setBatchTiming(request.getBatchTiming());
        profile.setPriorExperience(request.getPriorExperience());
        profile.setExperienceDescription(request.getExperienceDescription());
        profile.setSkillLevel(request.getSkillLevel());
        profile.setWhyJoin(request.getWhyJoin());
        profile.setCareerGoal(request.getCareerGoal());
        profile.setMessage(request.getMessage());

        return profileRepository.save(profile);
    }

    // 🔥 Get Profile
    public Profile getProfileByUserId(String userId) {

        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    // 🔥 Update Image URL (called from ImageController)
    public void updateProfileImage(String userId, String imageUrl) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setImageUrl(imageUrl);
        profileRepository.save(profile);
    }


    // 🔥 Get All Profiles
    public List<Profile> getAllProfiles() {

        List<Profile> profiles = profileRepository.findAll();

        // Attach image URL to each profile
        for (Profile profile : profiles) {
            Image image = imageRepository.findByProfileId(profile.getProfileId());
            if (image != null) {
                profile.setImageUrl(image.getImageUrl());
            }
        }

        return profiles;
    }

    // 🔥 Get Profile By ProfileId
    public Profile getProfileById(String profileId) {

        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        Image image = imageRepository.findByProfileId(profileId);
        if (image != null) {
            profile.setImageUrl(image.getImageUrl());
        }

        return profile;
    }
}
