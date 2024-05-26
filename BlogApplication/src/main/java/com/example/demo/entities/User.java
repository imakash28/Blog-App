package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="name", nullable=false, length=100)
	private String name;
	private String email;
	private String password;
	private String about;
	
	// One User has many posts  
	@JsonIgnore
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL/*(ALL->Parents as well as child, LAZY->Only parents not child)*/)
	private List<Post> posts=new ArrayList<>(); //the post consist of single category so the category is annotated with Many to one in post class 
	
}
