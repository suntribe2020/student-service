package com.example.studentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public boolean isSsn() {
        return ssn != null;
    }
    public boolean isFirstName() {
        return firstName != null;
    }
    public boolean isLastName() {
        return lastName != null;
    }
    public boolean isAddress() {
        return address != null;
    }
    public boolean isAreaCode() {
        return areaCode != null;
    }
    public boolean isCity() {
        return city != null;
    }
    public boolean isEmail() {
        return email != null;
    }
    public boolean isCourseId()  {
        return courseId != null;
    }
}
