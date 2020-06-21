package com.nikhil.tacoclod.jdbc.repositories.template;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nikhil.tacoclod.jdbc.repositories.interfaces.TacoRepository;
import com.nikhil.tacoclod.model.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {
	private String INSERT_TACO="insert into Taco (name,createdAt) values(?,?)";
	private String INSERT_TACO_INGREDIENTS="insert into Taco_Ingredients(taco,ingredient) values(?,?)";
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Taco save(Taco design) {
		long tacoId = saveTacoInfo(design);
		design.setId(tacoId);
		/*
		 * for (String ingredient : design.getIngredients()) {    remove this code to work properly even though its incomplete
		 * saveIngredientToTaco(ingredient, tacoId); }
		 */
		return design;
	}

	private long saveTacoInfo(Taco design) {
		design.setCreatedAt(new Date());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
				INSERT_TACO,Types.BIGINT , Types.VARCHAR, Types.TIMESTAMP)
						.newPreparedStatementCreator(
								Arrays.asList(design.getName(), new Timestamp(design.getCreatedAt().getTime())));
		
		jdbcTemplate.update(psc,keyHolder);
		return keyHolder.getKey().longValue();
	}
	
	private void saveIngredientToTaco(String ingredient, long tacoId) {
		System.out.println(ingredient);
		jdbcTemplate.update(INSERT_TACO_INGREDIENTS, tacoId, ingredient.charAt(0));

	}

}
