package com.Elite.Erum_Makeover.Controller;
import com.Elite.Erum_Makeover.DTO.ProfileRequest;
import com.Elite.Erum_Makeover.Model.Profile;
import com.Elite.Erum_Makeover.Services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController
{
    private final ProfileService profileService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> createProfile(@ModelAttribute ProfileRequest request) {
        return ResponseEntity.ok(profileService.createProfile(request));
    }
}