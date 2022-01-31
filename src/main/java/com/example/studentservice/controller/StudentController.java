package com.example.studentservice.controller;

import com.example.studentservice.VO.ResponseTemplateVO;
import com.example.studentservice.entity.Student;
import com.example.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Katri Vidén
 * Date: 2021-12-21
 * Time: 13:46
 * Project: student-service
 * Copyright: MIT
 */

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PatchMapping("/updateStudent")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @GetMapping("/findByStudentId/{studentId}")
    public Student findByStudentId(@PathVariable("studentId") Long studentId) {
        return studentService.findByStudentId(studentId);
    }

    @GetMapping("/findAll")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/getStudentWithCourse/{studentId}")
    public ResponseTemplateVO getStudentWithCourse(@PathVariable("studentId") Long studentId) {
        return studentService.getStudentWithCourse(studentId);
    }

    @DeleteMapping("/deleteByStudentId/{studentId}")
    public String deleteByStudentId(@PathVariable("studentId") Long studentId) {
        return studentService.deleteByStudentId(studentId);
    }
}
