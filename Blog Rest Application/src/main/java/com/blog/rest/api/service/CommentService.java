package com.blog.rest.api.service;

import com.blog.rest.api.entity.Comment;
import com.blog.rest.api.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long id, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(long postId, long commentId);

    CommentDto updateCommentById(long postId, long commentId, CommentDto comment);

    void deleteComment(Long postId, Long commentId);

}
