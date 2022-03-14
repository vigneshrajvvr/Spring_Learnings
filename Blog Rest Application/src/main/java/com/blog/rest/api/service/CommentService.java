package com.blog.rest.api.service;

import com.blog.rest.api.entity.Comment;
import com.blog.rest.api.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(long id, CommentDto commentDto);

}
