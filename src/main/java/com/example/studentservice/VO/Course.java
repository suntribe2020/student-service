package com.example.studentservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Katri Vidén
 * Date: 2021-12-22
 * Time: 11:46
 * Project: student-service
 * Copyright: MIT
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private Long id;
    private String courseId;
    private String courseTitle;
    private String duration;
}
