package com.dev.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

public class Student {
    private String name;
    private int id;
    public static List<Student> studentList;
    
    static {
        StudentRepository();
    }
    public Student(int id, String name) {
        this(name);
        this.id = id;
    }
    public Student(String name){
        this.name = name;
    }
    public Student(){
        
    }
    public int getId() {
        return id;
    }
    
    private static void StudentRepository(){
        studentList = new ArrayList<>(asList(new Student(1, "Matheus"), new Student(2,"Santana")));
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }
}