package com.mitocode.blog.blogapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.blog.blogapi.payload.CommentDto;
import com.mitocode.blog.blogapi.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId")Long postId,
			@RequestBody CommentDto commentDto){
		return new ResponseEntity<>(commentService.createComment(postId,commentDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId")Long postId) {
		return commentService.getCommentsByPostId(postId);
	}
}
