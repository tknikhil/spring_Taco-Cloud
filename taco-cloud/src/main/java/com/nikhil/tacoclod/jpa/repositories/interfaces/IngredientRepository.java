package com.nikhil.tacoclod.jpa.repositories.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.nikhil.tacoclod.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{

}
