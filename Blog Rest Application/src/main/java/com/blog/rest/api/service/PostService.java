package com.blog.rest.api.service;

import com.blog.rest.api.entity.Post;
import com.blog.rest.api.payload.PostDto;
import com.blog.rest.api.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePostById(Long id);
}
