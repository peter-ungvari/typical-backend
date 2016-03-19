package com.github.peterungvari.typicalbackend;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author jupi
 */
@SpringBootApplication
@PropertySource("classpath:sql.properties.xml")
public class Application {
    
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public PersonService personService() {
	return new PersonService(personDao());
    }
    
    @Bean
    public PersonDao personDao() {
	PersonDao personDao = new PersonDao(jdbcTemplate);
	personDao.setFindAllPersonSql(findAllPersonSql);
	personDao.setInsertPersonSql(insertPersonSql);
	return personDao;
    }
    
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    @Value("${sql.findAllPerson}")
    private String findAllPersonSql;
    
    @Value("${sql.insertPerson}")
    private String insertPersonSql;
    
    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }    

}
