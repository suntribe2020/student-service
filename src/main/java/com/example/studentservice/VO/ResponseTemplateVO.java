package com.example.studentservice.VO;

import com.example.studentservice.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Katri Vidén
 * Date: 2021-12-22
 * Time: 11:48
 * Project: student-service
 * Copyright: MIT
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private Student student;
    private List<Course> course;
}
