package com.mitocode.blog.blogapi.service;

import java.util.List;

import com.mitocode.blog.blogapi.payload.CommentDto;

public interface CommentService { // extends GenericCrudService<Comment, Long>
	CommentDto createComment(Long PostId, CommentDto commentDto);

	List<CommentDto> getCommentsByPostId(Long postId);

	CommentDto getCommentById(Long postId, Long commentId);

	CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);

	void deleteComment(Long postId, Long commentId);

}
