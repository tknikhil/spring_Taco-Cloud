package com.nikhil.tacoclod.jdbc.repositories.interfaces;

import com.nikhil.tacoclod.model.Order;

public interface OrderRepository {
	Order save(Order order);
}
