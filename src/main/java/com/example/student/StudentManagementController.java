package com.example.student;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
    private static List<Student> students = Arrays.asList(
            new Student(1L, "James Bond"),
            new Student(2L, "Sylvester Stallon"),
            new Student(3L, "Antonio farouz")

    );

   // hasRole('ROLE_')  hasAuthority('permission') hasAnyRole('ROLE_') hasAnyAuthority('permission')

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    public List<Student> getStudents() {
        System.out.println("getAllStudents");
        return students;
    }

    @GetMapping(path="{studentId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_ADMINTRAINEE')")
    public Student getStudent(@PathVariable("studentId") Long studentId){
        System.out.println("getAllStudentById");
        return students.stream()
                .filter(student-> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(()-> new IllegalStateException("Student "+studentId+" doesnot exits"));
    }
    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public Student registerStudent(@RequestBody Student student){
        System.out.println("registering student");
         return student;
    }


    @PutMapping(path="{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent( @PathVariable("studentId") Long studentId,
                               @RequestBody Student newStudent){
        System.out.println("update student");
        System.out.printf("%s %s",studentId,newStudent);
    }


    @DeleteMapping(path="{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") @NotNull Long studentId){
        System.out.println("delete student");
        System.out.println(studentId);
    }
}
