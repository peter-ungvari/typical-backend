package com.github.peterungvari.typicalbackend;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jupi
 */
@Repository
public class PersonDao {
    
    // SQL data accessor configured by spring boot based on settings of application.properties
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    @Autowired
    private Environment env; // Use environment to get queries from xml properties file.
    
    @Autowired
    private PersonRowMapper rowMapper;
    
    public List<Person> findAll() {
	return jdbcTemplate.query(env.getRequiredProperty("sql.person.findAll"), rowMapper);
    }

    public Person findByName(String name) {
	MapSqlParameterSource params = new MapSqlParameterSource("name", name);
	return jdbcTemplate.queryForObject(env.getRequiredProperty("sql.person.findByName"), params, rowMapper);
    }
    
    public List<Person> findNextPage(String afterName, int maxResults) {
	MapSqlParameterSource params = new MapSqlParameterSource("name", afterName).addValue("limit", maxResults);
	return jdbcTemplate.query(env.getRequiredProperty("sql.person.findNextPage"), params, rowMapper);
    }
    
    public void create(String name, int age) {
	MapSqlParameterSource params = new MapSqlParameterSource()
		.addValue("name", name)
		.addValue("age", age);
	jdbcTemplate.update(env.getRequiredProperty("sql.person.insert"), params);
    }
    
    public void delete(String name) {
	MapSqlParameterSource params = new MapSqlParameterSource("name", name);
	jdbcTemplate.update(env.getRequiredProperty("sql.person.deleteByName"), params);
    }
    
}
