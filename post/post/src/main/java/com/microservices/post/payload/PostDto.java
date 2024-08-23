package com.microservices.post.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	private String postId;
	private String title;
	private String description;
	private String content;
	
	
	List<Comment> comments;

}
