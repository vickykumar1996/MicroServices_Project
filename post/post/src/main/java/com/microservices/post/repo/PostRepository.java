package com.microservices.post.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservices.post.entity.Post;

public interface PostRepository  extends JpaRepository<Post, String>{

}
