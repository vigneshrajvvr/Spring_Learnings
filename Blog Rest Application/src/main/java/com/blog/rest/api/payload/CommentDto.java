package com.blog.rest.api.payload;

import com.blog.rest.api.entity.Comment;
import lombok.Data;

import java.util.Set;

@Data
public class CommentDto {

    private Long id;
    private String name;
    private String body;
    private String email;

}
