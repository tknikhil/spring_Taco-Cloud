package com.nikhil.tacoclod.jdbc.repositories.template;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nikhil.tacoclod.jdbc.repositories.interfaces.IngredientRepocitories;
import com.nikhil.tacoclod.model.Ingredient;

import lombok.extern.slf4j.Slf4j;
 
 
/*If you having Exception that Application failed to Start  like in mine
 * Consider defining a bean of type 'com.nikhil.taco.jdbc.repositories.interfaces.IngredientRepocitories' in your configuration.
 * there are two sol.
 * 1 : check out the package there must be some sort of spelling mistake because of that @SpringBootApplication can't reach to your class
 * 2 : add these
 * 				@ComponentScans(
    					value = {
        					@ComponentScan("tacoclod"),
        					@ComponentScan("taco")
    							})
by these you'r explaining @SpringBootApplication that there is another package that you have to scan also.
*/
@Slf4j
@Repository
public class JdbcIngredientRepository implements IngredientRepocitories {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Iterable<Ingredient> findAll() {
		
		return jdbcTemplate.query("select id, name,type from ingredient", this::mapRowToIngrident);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbcTemplate.update("insert into ingredient (id,name,type)values(?,?,?,?)", ingredient.getId(),
				ingredient.getName(), ingredient.getType());
		return ingredient;
	}

	@Override
	public Ingredient findOne(String id) {
		return jdbcTemplate.queryForObject("select id, name,type from ingredient where id=?", this::mapRowToIngrident,
				id);
	}

	private Ingredient mapRowToIngrident(ResultSet rs, int rowNum) throws SQLException {
		log.debug("Ingredient mapRowToIngrident");
		return new Ingredient(rs.getString("id"), rs.getString("name"), Ingredient.Type.valueOf(rs.getString("type")));
	}

}
