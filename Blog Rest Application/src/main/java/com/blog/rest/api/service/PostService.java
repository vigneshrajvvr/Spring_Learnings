package com.blog.rest.api.service;

import com.blog.rest.api.entity.Post;
import com.blog.rest.api.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);

}
