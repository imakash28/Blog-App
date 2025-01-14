package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PostDto;
import com.example.demo.entities.Category;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.PostRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.services.PostService;
@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId ) {
		
		User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
		
		Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepository.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost=this.postRepository.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		this.postRepository.delete(post);
	}

	@Override
	public List<PostDto> getAllPosts(Integer pageNumber,Integer pageSize) {
		Pageable p=PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost=this.postRepository.findAll(p);
		List<Post> allPosts=pagePost.getContent();
		//List<Post> posts=this.postRepository.findAll();
		List<PostDto> postDtos=allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<Post> posts = this.postRepository.findByCategory(cat);
		List<PostDto> postDtos = posts.stream()
			    .map((post) -> this.modelMapper.map(post, PostDto.class))
			    .collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<Post> getPostByUser(Integer userId) {
		User user =this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
		
		List<Post> posts= this.postRepository.findByUser(user);
		List<PostDto> postDtos = posts.stream()
			    .map((post) -> this.modelMapper.map(postDtos, Post.class))
			    .collect(Collectors.toList());
		return post;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
