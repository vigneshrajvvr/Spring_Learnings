package com.practice.vvr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.vvr.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
