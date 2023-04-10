package com.mitocode.blog.blogapi.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.blog.blogapi.payload.PostDto;
import com.mitocode.blog.blogapi.payload.PostResponseDto;
import com.mitocode.blog.blogapi.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	PostService postService;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
	
	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid@RequestBody PostDto postDto){
		return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@PathVariable(value = "id")Long postId,@RequestBody PostDto postDto){
		return new ResponseEntity<>(postService.updatePost(postDto,postId),HttpStatus.OK);
	}
	
	
	@GetMapping()
	public PostResponseDto getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
		return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
	}

	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(value = "id") Long id){
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePostById(@PathVariable(value = "id") Long id){
		postService.deletePostById(id);
		return new ResponseEntity<>("Post was deleted succesfully", HttpStatus.OK);
	}
	
}
