package com.Elite.Erum_Makeover.Controller;
import com.Elite.Erum_Makeover.DTO.ProfileRequest;
import com.Elite.Erum_Makeover.Model.Image;
import com.Elite.Erum_Makeover.Model.Profile;
import com.Elite.Erum_Makeover.Repository.ImageRepository;
import com.Elite.Erum_Makeover.Repository.ProfileRepository;
import com.Elite.Erum_Makeover.Services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProfileController {

    private final ProfileService profileService;
    private final ProfileRepository profileRepository;
    private final ImageRepository imageRepository;
    // ✅ Create Profile (PURE JSON)
    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody ProfileRequest request) {
        return ResponseEntity.ok(profileService.createProfile(request));
    }

    @GetMapping("/{id}")
    public Map<String, Object> getProfile(@PathVariable String id) {

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        String imageUrl = null;

        if (profile.getImageid() != null) {
            Image image = imageRepository
                    .findById(profile.getImageid())
                    .orElse(null);

            if (image != null) {
                imageUrl = image.getImageUrl();
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("profile", profile);
        response.put("imageUrl", imageUrl);

        return response;
    }
}