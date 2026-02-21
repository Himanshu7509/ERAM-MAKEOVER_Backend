package com.Elite.Erum_Makeover.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="courses")
@Getter
@Setter
public class Course
{
    @Id
    private String courseId;

    private String title;
    private String description;
    private Double price;

    private String duration;   // "6 Months"
    private String level;      // "Beginner" / "Advanced"
    private String imageUrl;   // course banner image

    private List<String> whatYouWillLearn;
}
