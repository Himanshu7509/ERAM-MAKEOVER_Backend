package com.Elite.Erum_Makeover.Controller;


import com.Elite.Erum_Makeover.Model.Image;
import com.Elite.Erum_Makeover.Services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
    @RequestMapping("/api/images")
@CrossOrigin(origins = "*")
public class ImageController {

    @Autowired
    private S3Service s3Service;

    public ImageController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Image> uploadImage(
            @RequestParam("file") MultipartFile file)
    {

        Image image = s3Service.uploadFile(file, "ProfileImage");
        return ResponseEntity.ok(image);
    }
}
