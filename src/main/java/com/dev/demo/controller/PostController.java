package com.dev.demo.controller;

import com.dev.demo.model.Post;
import com.dev.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("posts")
public class PostController {
    private PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    @PostMapping
    public ResponseEntity<?> save( @RequestBody Post post){
        System.out.println("ae ae"+post);
       // postRepository.save(post);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
