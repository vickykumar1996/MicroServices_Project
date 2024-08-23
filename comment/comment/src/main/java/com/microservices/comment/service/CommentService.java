package com.microservices.comment.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.microservices.comment.config.RestTemplateConfig;
import com.microservices.comment.entity.Comment;
import com.microservices.comment.payload.Post;
import com.microservices.comment.repos.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private RestTemplateConfig restTemplate;

	public Comment saveComment(Comment comment) {
		Post post = restTemplate.getRestTemplate()
				.getForObject("http://localhost:8080/api/posts/" + comment.getPostId(), Post.class);

		if (post != null) {
			String commentId = UUID.randomUUID().toString();
			comment.setCommentId(commentId);
			Comment savedComment = commentRepository.save(comment);
			return savedComment;
		} else {
			return null;
		}

	}
	
     @GetMapping("{postId}")
	public List<Comment> getAllCommentsByPostId( String postId) {
		// TODO Auto-generated method stub
		List<Comment> comments = commentRepository.findByPostId(postId);
		return comments;
	}
}
