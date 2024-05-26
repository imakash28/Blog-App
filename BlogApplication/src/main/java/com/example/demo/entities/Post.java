package com.example.demo.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="post")
@Getter
@Setter 
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name = "post_title", length=100, nullable = false)
	private String title;
	
	@Column(length=10000)
	private String content;
	private String imageName;
	private Date addedDate; 
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="category_id") 	// this column is created by joining categoryId and category
	private Category category;		// It represent the post from which category
	
	
	@JsonIgnore
	@ManyToOne
	private User user;		//It represents the post from which user
}
