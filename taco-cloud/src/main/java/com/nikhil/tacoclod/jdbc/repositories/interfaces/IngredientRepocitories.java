package com.nikhil.tacoclod.jdbc.repositories.interfaces;

import com.nikhil.tacoclod.model.Ingredient;

public interface IngredientRepocitories {

	Iterable<Ingredient> findAll();
	Ingredient save(Ingredient ingredient);
	Ingredient findOne(String id);
 }
