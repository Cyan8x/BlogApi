package com.mitocode.blog.blogapi.service;

import com.mitocode.blog.blogapi.payload.PostDto;
import com.mitocode.blog.blogapi.payload.PostResponseDto;

public interface PostService {
	PostDto createPost(PostDto postDto);
	PostResponseDto getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
	PostDto getPostById(Long id);
	PostDto updatePost(PostDto postDto, Long id);
	void deletePostById(Long id);
}
