package com.microservices.comment.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.comment.entity.Comment;



public interface CommentRepository  extends JpaRepository<Comment, String>{

	List<Comment> findByPostId(String postId);// this abstract method automatically will get  coverted into HQL query
	
}
