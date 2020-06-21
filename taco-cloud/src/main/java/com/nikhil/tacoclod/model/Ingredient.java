package com.nikhil.tacoclod.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor														 //comming from jdbc
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)					//for jpa to make constructor private
@Entity																			
public class Ingredient {
	@Id																			//for jpa you have to anotate id property with @Id you can skip that in jdbc
	private final String id;
	private final String name;
	private final Type type;
	
	public static enum Type{
		WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
	}
}
