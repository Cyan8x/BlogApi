package com.mitocode.blog.blogapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitocode.blog.blogapi.entity.Post;
import com.mitocode.blog.blogapi.exception.ResourceNotFoundException;
import com.mitocode.blog.blogapi.payload.PostDto;
import com.mitocode.blog.blogapi.payload.PostResponseDto;
import com.mitocode.blog.blogapi.repository.PostRepository;
import com.mitocode.blog.blogapi.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	private ModelMapper mapper;

	public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {
		super();
		this.postRepository = postRepository;
		this.mapper = mapper;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = mapToEntity(postDto);
		Post newPost = postRepository.save(post);
		return mapToDto(newPost);
	}
	
	@Transactional(readOnly = true)
	@Override
	public PostResponseDto getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir){
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
				?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
		
		Page<Post> posts = postRepository.findAll(pageable);
		
		List<Post> listOfPosts = posts.getContent();
		
		List<PostDto> content = listOfPosts.stream().map(post->mapToDto(post)).collect(Collectors.toList());
		
		PostResponseDto postResponse = new PostResponseDto();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
	}

	@Transactional(readOnly = true)
	@Override
	public PostDto getPostById(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		return mapToDto(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());
		post.setTitle(postDto.getTitle());
		Post updatePost = postRepository.save(post);
		return mapToDto(updatePost);
	}

	@Override
	public void deletePostById(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

		postRepository.delete(post);
	}

	// Se crean estos metosos en el caso no se tenga implementado el Mapper, que
	// permite la conversion de un dato a otro

	// Entity to Dto
	private PostDto mapToDto(Post post) {
		PostDto postDto = mapper.map(post, PostDto.class);
		
//		PostDto postDto = new PostDto();
//		postDto.setTitle(post.getTitle());
//		postDto.setId(post.getId());
//		postDto.setDescription(post.getDescription());
//		postDto.setContent(post.getContent());
		return postDto;
	}

	// Dto to Entity
	private Post mapToEntity(PostDto postDto) {
		Post post = mapper.map(postDto, Post.class);
		
//		Post post = new Post();
//		post.setTitle(postDto.getTitle());
//		post.setId(postDto.getId());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());
		return post;
	}

}
