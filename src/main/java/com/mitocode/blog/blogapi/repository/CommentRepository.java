package com.mitocode.blog.blogapi.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mitocode.blog.blogapi.entity.Comment;

@Repository
public interface CommentRepository extends GenericRepository<Comment, Long> {
	List<Comment> findByPostId(long postId);
}
