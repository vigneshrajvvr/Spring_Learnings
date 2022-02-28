package com.blog.rest.api.service.implementation;

import com.blog.rest.api.entity.Post;
import com.blog.rest.api.exception.ResourceNotFoundException;
import com.blog.rest.api.payload.PostDto;
import com.blog.rest.api.repository.PostRepository;
import com.blog.rest.api.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert DTO into entity
        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);

        return mapToDto(newPost);
    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize) {

        // create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Post> posts = postRepository.findAll(pageable);

        // get content for post object
        List<Post> listOfPosts = posts.getContent();

        return listOfPosts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {

        // get the post by id
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);

        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(Long id) {

        Post post = postRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Post", "id", id));

        postRepository.delete(post);

    }

    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;
    }


}
