package com.github.peterungvari.typicalbackend;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jupi
 */
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;
    
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public List<Person> hello() {
	return personService.findAllPerson();
    }
    
    @RequestMapping(value = "hello", method = RequestMethod.POST)
    public void insertDummyPerson() {
	personService.insertPerson("Otto", 15);
    }
    
    @RequestMapping(value = "insertPersonParams", method = RequestMethod.POST, 
	    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void insertPersonParams(@RequestParam("name") String name, @RequestParam("age") int age) {
	personService.insertPerson("Otto", 15);
    }
    
    @RequestMapping(value = "insertPersonObject", method = RequestMethod.POST, 
	    consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertPersonObject(@RequestBody Person person) {
	personService.insertPerson(person.getName(), person.getAge());
    }
}
