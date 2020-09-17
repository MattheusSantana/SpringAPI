package com.dev.demo.repository;

import com.dev.demo.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
   // List<Post> findByTituloIgnoreCaseContaining(String titulo);


}
