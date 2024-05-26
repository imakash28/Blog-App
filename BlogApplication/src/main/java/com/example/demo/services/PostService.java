package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.PostDto;
import com.example.demo.entities.Post;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	List<PostDto> getAllPost();

	List<PostDto> getAllPosts(Integer PageNumber, Integer pageSize);
	
	PostDto getPostById(Integer postId);
	
	List<Post> getPostByCategory(Integer categoryId);
	
	List<Post> getPostByUser(Integer userId);
	
	List<Post> searchPosts(String keyword);
	
}
