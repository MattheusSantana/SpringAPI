package com.dev.demo.repository;

import com.dev.demo.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    List<Student> findByName(String name);
    List<Student> findByNameIgnoreCaseContaining(String name);
}
