package com.microservices.post.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.post.config.RestTemplateConfig;
import com.microservices.post.entity.Post;
import com.microservices.post.payload.PostDto;
import com.microservices.post.repo.PostRepository;
@Service
public class PostService {
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private RestTemplateConfig restTemplate;

	public Post savePost(Post post) {
		String postId = UUID.randomUUID().toString();
		post.setId(postId);
		Post savedPost = postRepo.save(post);
		return savedPost;
	}

	public Post findPostById(String postId) {
		// TODO Auto-generated method stub
		Post post=postRepo.findById(postId).get();
		return post;
	}

	public PostDto getPostWithComments(String postId) {
		// TODO Auto-generated method stub
	Post	post=postRepo.findById(postId).get();
		ArrayList comments = restTemplate.getRestTemplate().getForObject("http://COMMENT-SERVICE/api/comments/"+postId, ArrayList.class);
		PostDto postDto= new PostDto();
		postDto.setPostId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setDescription(post.getDescription());
		postDto.setContent(post.getContent());
		postDto.setComments(comments);
		
		return postDto;
	} 

}
