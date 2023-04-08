package com.mitocode.blog.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitocode.blog.blogapi.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> { // extends GenericRepository<Post, Long>
	
}
