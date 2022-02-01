package com.blog.rest.api.controller;

import com.blog.rest.api.payload.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/posts")
public class PostController {

    @PostMapping
    public ResponseEntity<PostDto> createPosts(@RequestBody PostDto postDto) {
        return new ResponseEntity<PostDto>(HttpStatus.CREATED);
    }

}
