package com.microservices.post.controller;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.post.entity.Post;
import com.microservices.post.payload.PostDto;
import com.microservices.post.service.PostService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
@RequestMapping("/api/posts")
public class PostContoller {
	@Autowired
	private PostService postService;
	
	@PostMapping
	public ResponseEntity<Post> savePost(@RequestBody Post post){
		Post newPost=postService.savePost(post);
		return new ResponseEntity<>(newPost,HttpStatus.CREATED);	
	}
//http://localhost:8081/api/posts/{postId}	
	
	@GetMapping("/{postId}")
	public Post getPostByPostId(@PathVariable String postId) {
		Post post=postService.findPostById(postId);
		return post;
		
	}
	//http://localhost:8081/api/posts/{postId}/comments
	@GetMapping("/{postId}/comments")
	@CircuitBreaker(name="commentBreaker",fallbackMethod="commentFallback")
	public ResponseEntity<PostDto> getPostWithComments(@PathVariable String postId){
	PostDto postDto=postService.getPostWithComments(postId);
	
	return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	public ResponseEntity<PostDto> commmentFallback(String postId,Exception ex){
		System.out.println("Fallback is executed because service is down: "+ ex.getMessage());
		ex.printStackTrace();
		
		PostDto dto=new PostDto();
		dto.setPostId("1234");
		dto.setTitle("Service Down");
		dto.setContent("service down");
		dto.setDescription("service down");
		//dto.setComments(Arrays.("service down"));
		return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);
	}

}
