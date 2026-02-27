package com.Elite.Erum_Makeover.Controller;


import com.Elite.Erum_Makeover.Model.Image;
import com.Elite.Erum_Makeover.Model.Profile;
import com.Elite.Erum_Makeover.Model.User;
import com.Elite.Erum_Makeover.Repository.ProfileRepository;
import com.Elite.Erum_Makeover.Repository.UserRepository;
import com.Elite.Erum_Makeover.SecurityConfig.JwtUtil;
import com.Elite.Erum_Makeover.Services.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
    @RequestMapping("/api/images")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ImageController {

    private final JwtUtil JwtUtil;
    private final ProfileRepository profileRepository;
    private final S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadProfileImage(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam("file") MultipartFile file
    ) {
        String token = authHeader.substring(7);
        String userId = JwtUtil.extractUserId(token);

// 🔥 Upload to S3
        Image image = s3Service.uploadFile(file, "ProfileImage");

        String imageUrl = image.getImageUrl();

// 🔥 Find profile by userId
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setImageUrl(imageUrl);
        profileRepository.save(profile);

        return ResponseEntity.ok(imageUrl);
    }
}
