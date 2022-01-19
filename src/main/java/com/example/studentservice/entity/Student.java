package com.example.studentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Katri Vidén
 * Date: 2021-12-21
 * Time: 13:13
 * Project: student-service
 * Copyright: MIT
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;

    private String ssn;
    private String firstName;
    private String lastName;
    private String address;
    private String areaCode;
    private String city;
    private String email;
    private String courseId;

}
