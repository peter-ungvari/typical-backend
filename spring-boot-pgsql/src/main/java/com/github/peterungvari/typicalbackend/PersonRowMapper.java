package com.github.peterungvari.typicalbackend;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author jupi
 */
public class PersonRowMapper implements RowMapper<Person> {


    @Override
    public Person mapRow(ResultSet rs, int i) throws SQLException {
	Person person = new Person();
	
	person.setName(rs.getString("name"));
	person.setAge(rs.getInt("age"));
	return person;
    }
    
}
