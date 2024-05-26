package com.example.demo.dto;

import java.util.Date;

import com.example.demo.entities.Category;
import com.example.demo.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private int postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private Category category;
	private User user;
	
}
