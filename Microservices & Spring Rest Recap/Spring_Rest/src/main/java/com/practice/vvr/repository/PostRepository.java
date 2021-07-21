package com.practice.vvr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.vvr.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

}
