package com.microservices.comment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Post {
	private String postId;
	private String title;
	private String description;
	private String content;
	

}
