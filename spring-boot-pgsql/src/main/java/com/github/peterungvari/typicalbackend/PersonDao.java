package com.github.peterungvari.typicalbackend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author jupi
 */
public class PersonDao {
    
    private String findAllPersonSql;
    private String insertPersonSql;
    private NamedParameterJdbcTemplate jdbcTemplate;

    public PersonDao(NamedParameterJdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }
    
    public void setFindAllPersonSql(String findAllPersonSql) {
	this.findAllPersonSql = findAllPersonSql;
    }

    public void setInsertPersonSql(String insertPersonSql) {
	this.insertPersonSql = insertPersonSql;
    }
 
    public List<Person> findAllPerson() {
	List<Map<String, Object>> results = jdbcTemplate.queryForList(findAllPersonSql, new MapSqlParameterSource());
	List<Person> persons = new ArrayList<>();
	
	for(Map<String, Object> row : results) {
	    Person person = new Person();
	    person.setName((String)row.get("name"));
	    person.setAge((int)row.get("age"));
	    persons.add(person);
	}
	
	return persons;
    }
    
    public List<Person> findAllPerson2() {
	return jdbcTemplate.query(findAllPersonSql, new PersonRowMapper());
    }
    
    public void insertPerson(String name, int age) {
	MapSqlParameterSource params = new MapSqlParameterSource()
		.addValue("name", name)
		.addValue("age", age);
	jdbcTemplate.update(insertPersonSql, params);
    }
    
}
