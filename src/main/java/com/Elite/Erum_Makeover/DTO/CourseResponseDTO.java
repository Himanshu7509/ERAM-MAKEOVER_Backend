package com.Elite.Erum_Makeover.DTO;


import lombok.Data;
import java.util.List;

@Data
public class CourseResponseDTO {

    private String courseId;
    private String title;
    private String description;
    private Double price;
    private String duration;
    private String level;
    private List<String> whatYouWillLearn;
    private String imageUrl;

}