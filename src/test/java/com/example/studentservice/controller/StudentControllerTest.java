package com.example.studentservice.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Katri Vidén
 * Date: 2022-01-04
 * Time: 16:49
 * Project: student-service
 * Copyright: MIT
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
class StudentControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    RestTemplate restTemplate;

    private static final String TEST_ID = "" + System.currentTimeMillis();
    private static final String STUDENT_ID = "studentId";
    private static final String SSN = "ssn";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String ADDRESS = "address";
    private static final String AREA_CODE = "areaCode";
    private static final String CITY = "city";
    private static final String EMAIL = "email";
    private static final String COURSE_ID = "courseId";

    @Test
    void shouldBeAbleToSaveStudent() throws JSONException {
        //Create a student
        JSONObject body = generateStudentBody();
        createNewStudent(body);

        //Find and get the created student
        String allStudentsAsJsonArray = getAllStudents();
        JSONObject testStudent = getCreatedTestStudent(allStudentsAsJsonArray);

        assertNotNull(testStudent);
        assertEquals(SSN + TEST_ID, testStudent.getString("ssn"));
        assertEquals(FIRST_NAME + TEST_ID, testStudent.getString("firstName"));
        assertEquals(LAST_NAME + TEST_ID, testStudent.getString("lastName"));
        assertEquals(ADDRESS + TEST_ID, testStudent.getString("address"));
        assertEquals(AREA_CODE + TEST_ID, testStudent.getString("areaCode"));
        assertEquals(CITY + TEST_ID, testStudent.getString("city"));
        assertEquals(EMAIL + TEST_ID, testStudent.getString("email"));
        assertEquals(COURSE_ID + TEST_ID, testStudent.getString("courseId"));

    }

    private void createNewStudent(JSONObject body) {
        webTestClient.post()
                .uri("/student/save")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body.toString())
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    private String getAllStudents() {
        return webTestClient.get()
                .uri("/student/findAll")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(String.class)
                .getResponseBody()
                .blockFirst();
    }

    private void deleteStudent(String studentId) {
        webTestClient.delete()
                .uri("/student/deleteByStudentId?id=" + studentId)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    private JSONObject generateStudentBody() throws JSONException {
        JSONObject body = new JSONObject();
        body.put(SSN, SSN + TEST_ID);
        body.put(FIRST_NAME, FIRST_NAME + TEST_ID);
        body.put(LAST_NAME, LAST_NAME + TEST_ID);
        body.put(ADDRESS, ADDRESS + TEST_ID);
        body.put(AREA_CODE, AREA_CODE + TEST_ID);
        body.put(CITY, CITY + TEST_ID);
        body.put(EMAIL, EMAIL + TEST_ID);
        body.put(COURSE_ID, COURSE_ID + TEST_ID);

        return body;
    }

    private JSONObject getCreatedTestStudent(String allStudentsAsJsonArray) throws JSONException {
        JSONArray allStudents = new JSONArray(allStudentsAsJsonArray);
        for (int i = 0; i < allStudents.length() ; i++) {
            JSONObject student = allStudents.getJSONObject(i);
            if (student.getString(EMAIL).endsWith(TEST_ID)) {
                return student;
            }
        }
        return null;
    }
}

