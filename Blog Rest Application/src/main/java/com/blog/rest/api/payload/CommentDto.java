package com.blog.rest.api.payload;

import com.blog.rest.api.entity.Comment;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class CommentDto {

    private Long id;
    /*
        name should not be null or empty
     */
    @NotEmpty(message = "Name should not be null or empty")
    private String name;
    /*
        email should not be null or empty
        email field validation
     */
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;
    /*
        comment body should not be null or empty
        comment must be minimum of 10 characters
     */
    @NotEmpty
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;
}
