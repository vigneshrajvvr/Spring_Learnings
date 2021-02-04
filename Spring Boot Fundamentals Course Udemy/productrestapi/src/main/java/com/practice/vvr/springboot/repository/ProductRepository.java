package com.practice.vvr.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.vvr.springboot.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
