package com.nikhil.tacoclod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.nikhil.tacoclod.jpa.repositories.interfaces.IngredientRepository;
import com.nikhil.tacoclod.model.Ingredient;

@Component
public class IngredientIdConverter implements  Converter<String, Ingredient>{
	
	private IngredientRepository ingredientRepository;
	@Autowired
	public IngredientIdConverter(IngredientRepository ingredientRepository) {
		this.ingredientRepository=ingredientRepository;
	}

	@Override
	public Ingredient convert(String id) {
		
		return ingredientRepository.findById(id).orElse(null);
	}

}
