package com.nikhil.tacoclod.jpa.repositories.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.nikhil.tacoclod.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
