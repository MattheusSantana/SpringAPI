package com.dev.demo.endpoint;

import com.dev.demo.error.CustomErrorType;
import com.dev.demo.model.Student;
import com.dev.demo.repository.StudentRepository;
import com.dev.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentEndpoint {
    
    @Autowired
    private DateUtil dateUtil;


    private final StudentRepository studentDAO;

    @Autowired
    public StudentEndpoint(StudentRepository studentDAO){
        this.studentDAO = studentDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll(){
        System.out.println("Date formated com injeção de dependência: "+ this.dateUtil.formatLocalDateTimeToDataBasePattern(LocalDateTime.now()));
        return new ResponseEntity<>(studentDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id){
       Optional<Student> student = studentDAO.findById(id);
        if(student == null) {
            return new ResponseEntity<>(
                    new CustomErrorType("Student Not Found"), HttpStatus.OK
            );
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student){
       studentDAO.save(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        studentDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student){
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}