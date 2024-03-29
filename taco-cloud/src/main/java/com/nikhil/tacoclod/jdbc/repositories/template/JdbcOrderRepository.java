package com.nikhil.tacoclod.jdbc.repositories.template;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikhil.tacoclod.jdbc.repositories.interfaces.OrderRepository;
import com.nikhil.tacoclod.model.Order;
import com.nikhil.tacoclod.model.Taco;

@Repository
public class JdbcOrderRepository implements OrderRepository {

	private SimpleJdbcInsert orderJdbcIncertor;
	private SimpleJdbcInsert orderTacoJdbcIncertor;
	private ObjectMapper objectMapper;
	
	public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
		this.orderJdbcIncertor=new SimpleJdbcInsert(jdbcTemplate).withTableName("Taco_Order").usingGeneratedKeyColumns("id");
		this.orderTacoJdbcIncertor=new SimpleJdbcInsert(jdbcTemplate).withTableName("Taco_Order_Taco");
		this.objectMapper =new ObjectMapper();
	}
	@Override
	public Order save(Order order) {
		
		order.setPlacedAt(new Date());
		long ordreId =saveOrderDetails(order);
		order.setId(ordreId);
		List<Taco> tacos =order.getTaco();
		for (Taco taco : tacos) {
			saveTacoToOrder(taco,ordreId);
		}
		return order;
	}
	private void saveTacoToOrder(Taco taco, long ordreId) {
		Map<String,Object> values=new HashMap<>();
		values.put("tacoOrder", ordreId);
		values.put("taco",taco.getId());
		orderTacoJdbcIncertor.execute(values);
	}
	private long saveOrderDetails(Order order) {
		@SuppressWarnings("unchecked")
		Map<String,Object>values= objectMapper.convertValue(order, Map.class);
		values.put("placedAt", order.getPlacedAt());
		
		long orderId=orderJdbcIncertor.executeAndReturnKey(values).longValue();
		return orderId;
	}

}
