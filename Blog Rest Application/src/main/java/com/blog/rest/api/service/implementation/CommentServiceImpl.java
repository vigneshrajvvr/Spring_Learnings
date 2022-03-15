package com.blog.rest.api.service.implementation;

import com.blog.rest.api.entity.Comment;
import com.blog.rest.api.entity.Post;
import com.blog.rest.api.exception.ResourceNotFoundException;
import com.blog.rest.api.payload.CommentDto;
import com.blog.rest.api.payload.PostDto;
import com.blog.rest.api.repository.CommentRepository;
import com.blog.rest.api.repository.PostRepository;
import com.blog.rest.api.service.CommentService;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.tokens.CommentToken;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = mapToEntity(commentDto);

        comment.setPost(post);

        commentRepository.save(comment);

        return mapToDto(comment);

    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map((comment) -> mapToDto(comment)).collect(Collectors.toList());
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        return comment;
    }
}
