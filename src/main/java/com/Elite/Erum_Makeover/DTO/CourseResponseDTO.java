package com.Elite.Erum_Makeover.DTO;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CourseResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String courseId;
    private String title;
    private String description;
    private Double price;
    private String duration;
    private String level;
    private List<String> whatYouWillLearn;
    private String imageUrl;

}