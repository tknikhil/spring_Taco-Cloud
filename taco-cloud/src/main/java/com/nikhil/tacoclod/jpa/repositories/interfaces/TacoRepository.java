package com.nikhil.tacoclod.jpa.repositories.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.nikhil.tacoclod.model.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long>{

}
