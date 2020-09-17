package com.dev.demo.endpoint;

import com.dev.demo.model.Student;
import com.dev.demo.repository.StudentRepository;
import com.dev.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<?> listAll(Pageable pageable){
        System.out.println("Date formated com injeção de dependência: "+ this.dateUtil.formatLocalDateTimeToDataBasePattern(LocalDateTime.now()));
        return new ResponseEntity<>(studentDAO.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id){
        Optional<Student> student = studentDAO.findById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid  @RequestBody Student student){
        studentDAO.save(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        studentDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student){
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        return new ResponseEntity<>(studentDAO.findByName(name), HttpStatus.OK);
    }

    @GetMapping(path = "/findByNameContaining/{name}")
    public ResponseEntity<?> findByNameIgnoreCaseContaining(@PathVariable String name){
        return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }


}