package com.github.peterungvari.typicalbackend;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jupi
 */
@Service
public class PersonService {
    
    @Autowired
    private PersonDao personDao;
    
    public List<Person> findAll() {
	return personDao.findAll();
    }
    
    public Person findByName(String name) {
	return personDao.findByName(name);
    }

    public List<Person> findNextPage(String afterName, int maxResults) {
	return personDao.findNextPage(afterName, maxResults);
    }
    
    public void create(String name, int age) {
	personDao.create(name, age);
    }

    public void delete(String name) {
	personDao.delete(name);
    }
    
}
