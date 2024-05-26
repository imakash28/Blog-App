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
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer categoryId;
	@Column(name="title", length=100, nullable = false)
	private String categoryTitle;
	@Column(name= "description")
	private String categoryDescription;
	
	/* a single category can contains multiple posts i.e. One TO Many , mapped by category column in post class, cascadeType ALL means
	 * if we remove the parent then its all child also get removed and if we add parents then child also get saved auto matically*/
	//One category has many post
	@JsonIgnore
	@OneToMany(mappedBy="category", cascade = CascadeType.ALL/*(ALL->Parents as well as child, LAZY->Only parents not child)*/)
	private List<Post> posts=new ArrayList<>(); //the post consist of single category so the category is annotated with Many to one in post class 
	
}
