package com.example.studentservice.service;

import com.example.studentservice.VO.Course;
import com.example.studentservice.VO.ResponseTemplateVO;
import com.example.studentservice.entity.Student;
import com.example.studentservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katri Vidén
 * Date: 2021-12-21
 * Time: 13:44
 * Project: student-service
 * Copyright: MIT
 */
@RequiredArgsConstructor
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String COURSE_SERVICE_ENDPOINT = "http://COURSE-SERVICE/course/findById/";

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        Student updateStudent = studentRepository.findByStudentId(student.getStudentId());
        if (student.isSsn()) {
            updateStudent.setSsn(student.getSsn());
        }
        if (student.isFirstName()) {
            updateStudent.setFirstName(student.getFirstName());
        }
        if (student.isLastName()) {
            updateStudent.setLastName(student.getLastName());
        }
        if (student.isAddress()) {
            updateStudent.setAddress(student.getAddress());
        }
        if (student.isAreaCode()) {
            updateStudent.setAreaCode(student.getAreaCode());
        }
        if (student.isCity()) {
            updateStudent.setCity(student.getCity());
        }
        if (student.isEmail()) {
            updateStudent.setEmail(student.getEmail());
        }
        if (student.isCourseId()) {
            updateStudent.setCourseId(student.getCourseId());
        }
        studentRepository.save(updateStudent);

        return updateStudent;
    }

    public Student findByStudentId(Long studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public String deleteByStudentId(Long studentId) {
        studentRepository.deleteByStudentId(studentId);
        return String.format("Student with id:%s is now deleted", studentId);
    }

    public ResponseTemplateVO getStudentWithCourse(Long studentId) {
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        Student student = studentRepository.findByStudentId(studentId);

        String[] courseArray = student.getCourseId().split(",");
        List<Course> courseList = new ArrayList<>();
        for (String courseId : courseArray) {
            //Call course service and get course info as a list, get courseId from student object
           courseList.add(restTemplate.getForObject(COURSE_SERVICE_ENDPOINT + courseId, Course.class));
        }

        responseTemplateVO.setStudent(student);
        responseTemplateVO.setCourse(courseList);

        return responseTemplateVO;
    }
}
