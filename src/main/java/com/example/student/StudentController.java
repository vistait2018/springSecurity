package com.example.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static List<Student>  students = Arrays.asList(
       new Student(1L,"James Bond"),
            new Student(2L,"Sylvester Stallon"),
            new Student(3L,"Antonio farouz")

    );


    @GetMapping()
    public List<Student> allStudents(){

       return students.stream()
               .collect(Collectors.toList());
    }
    @GetMapping(path="{studentId}")
    public Student getStudent(@PathVariable("studentId") Long studentId){
         return students.stream()
                 .filter(student-> studentId.equals(student.getStudentId()))
                 .findFirst()
                 .orElseThrow(()-> new IllegalStateException("Student "+studentId+" doesnot exits"));
    }


}
