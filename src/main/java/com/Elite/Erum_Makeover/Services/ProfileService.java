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

        if (request.getProfilePhoto() != null && !request.getProfilePhoto().isEmpty()) {
            imageUrl = s3Service.uploadFile(request.getProfilePhoto(), "profiles");
        }

        Profile profile = new Profile();
        profile.setFullName(request.getFullName());
        profile.setEmail(request.getEmail());
        profile.setPhoneNumber(request.getPhoneNumber());

        profile.setDateOfBirth(request.getDateOfBirth().toLocalDate());
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

        // ✅ Save S3 URL in DB
        profile.setProfilePhoto(imageUrl);

        return profileRepository.save(profile);
    }
}
