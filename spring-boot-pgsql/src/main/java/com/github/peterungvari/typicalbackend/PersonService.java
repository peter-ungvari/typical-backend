package com.github.peterungvari.typicalbackend;

import java.util.List;

/**
 *
 * @author jupi
 */
public class PersonService {
    
    private PersonDao personDao;

    public PersonService(PersonDao personDao) {
	this.personDao = personDao;
    }
    
    public List<Person> findAllPerson() {
	return personDao.findAllPerson2();
    }
    
    public void insertPerson(String name, int age) {
	personDao.insertPerson(name, age);
    }
}
