package com.nikhil.tacoclod.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;



@Data
@Entity
public class Taco {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date createdAt;
	@NotNull
	@Size(min = 5,message = "Name must be 5 character long")
	private String name;
	@ManyToMany(targetEntity = Ingredient.class)							//JPA
	@Size(min=1,message = "Choose at least 1 Ingredient")
	private List<Ingredient> ingredients;					//for Jdbc convert List<Ingredient> to List<String>
	
	@PrePersist
	void createdAt() {
		this.createdAt=new Date();											//added only in JPA
	}
}
