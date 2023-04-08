package com.mitocode.blog.blogapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mitocode.blog.blogapi.entity.Comment;
import com.mitocode.blog.blogapi.entity.Post;
import com.mitocode.blog.blogapi.exception.ResourceNotFoundException;
import com.mitocode.blog.blogapi.payload.CommentDto;
import com.mitocode.blog.blogapi.repository.CommentRepository;
import com.mitocode.blog.blogapi.repository.PostRepository;
import com.mitocode.blog.blogapi.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private ModelMapper mapper;
	
	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.mapper = mapper;
	}

	@Override
	public CommentDto createComment(Long postId, CommentDto commentDto) {
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
				
		Comment comment = mapToEntity(commentDto);
		comment.setPost(post);
		
		Comment newComment = commentRepository.save(comment);
				
		return mapToDto(newComment);
	}

	@Override
	public List<CommentDto> getCommentsByPostId(Long postId) {
		List<Comment> comments = commentRepository.findByPostId(postId);
		return comments.stream().map(comment->mapToDto(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComment(Long postId, Long commentId) {
		// TODO Auto-generated method stub
		
	}
	
	private CommentDto mapToDto(Comment comment) {
		CommentDto commentDto = mapper.map(comment, CommentDto.class);
		return commentDto;
	}

	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment = mapper.map(commentDto, Comment.class);
		return comment;
	}
	
}
