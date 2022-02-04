package com.blog.rest.api.service.implementation;

import com.blog.rest.api.entity.Post;
import com.blog.rest.api.payload.PostDto;
import com.blog.rest.api.repository.PostRepository;
import com.blog.rest.api.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert DTO into entity
        Post post = new Post();
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());

        Post newPost = postRepository.save(post);

        // convert entity to DTO
        postDto.setId(newPost.getId());
        postDto.setTitle(newPost.getTitle());
        postDto.setDescription(newPost.getDescription());
        postDto.setContent(newPost.getContent());

        return postDto;
    }
}
