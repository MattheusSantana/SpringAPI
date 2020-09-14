package com.dev.demo.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dev.demo.model.Student;
import com.dev.demo.util.DateUtil;

import java.util.List;
import static java.util.Arrays.asList;

import java.time.LocalDateTime;

@RestController
@RequestMapping("student")
public class StudentEndpoint {
    
    @Autowired
    private DateUtil dateUtil;
    
    
    @RequestMapping(method= RequestMethod.GET, path="/list")
    public List<Student> listAll(){
        System.out.println("Date formated com injeção de dependência: "+ this.dateUtil.formatLocalDateTimeToDataBasePattern(LocalDateTime.now()));
        return asList(new Student("Matheus"), new  Student("Emmanoel"));
    }
}