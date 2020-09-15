package com.dev.demo.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dev.demo.error.CustomErrorType;
import com.dev.demo.model.Student;
import com.dev.demo.util.DateUtil;


import java.time.LocalDateTime;

@RestController
@RequestMapping("students")
public class StudentEndpoint {
    
    @Autowired
    private DateUtil dateUtil;
    
    
    @GetMapping
    public ResponseEntity<?> listAll(){
        System.out.println("Date formated com injeção de dependência: "+ this.dateUtil.formatLocalDateTimeToDataBasePattern(LocalDateTime.now()));
        return new ResponseEntity<>(Student.studentList, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id){
        Student student = new Student();
        student.setId(id);
        int index = Student.studentList.indexOf(student);
        if (index == -1)
            return new ResponseEntity<>(
                new CustomErrorType("Student Not Found"), HttpStatus.OK
                );
        return new ResponseEntity<>(student.studentList.get(index), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student){
        Student.studentList.add(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Student student){
        Student.studentList.remove(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}