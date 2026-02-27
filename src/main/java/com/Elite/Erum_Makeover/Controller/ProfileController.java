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
@CrossOrigin(origins = "*")
public class ProfileController {

    private final ProfileService profileService;

    // ✅ Create Profile (PURE JSON)
    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody ProfileRequest request) {
        return ResponseEntity.ok(profileService.createProfile(request));
    }

    // ✅ Get Profile With Image
    @GetMapping("/{id}")
    public ResponseEntity<?> getProfile(@PathVariable String id) {
        return ResponseEntity.ok(profileService.getProfile(id));
    }
}