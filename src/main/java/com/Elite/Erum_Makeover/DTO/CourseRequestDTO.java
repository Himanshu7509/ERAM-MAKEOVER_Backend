package com.Elite.Erum_Makeover.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO
{

    private String title;
    private String description;
    private Double price;
    private String duration;
    private String level;

    private List<String> whatYouWillLearn;
}
