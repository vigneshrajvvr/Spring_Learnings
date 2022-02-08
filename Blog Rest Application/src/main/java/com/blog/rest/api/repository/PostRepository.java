package com.blog.rest.api.repository;

import com.blog.rest.api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/*
Post table repository interface
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
