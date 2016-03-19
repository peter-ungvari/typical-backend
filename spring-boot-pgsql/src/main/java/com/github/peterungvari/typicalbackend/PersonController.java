package com.github.peterungvari.typicalbackend;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonService personService;
    
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Person> list() {
	return personService.findAll();
    }
    
    @RequestMapping(value = "list/after/{name}/max/{max}", method = RequestMethod.GET)
    public List<Person> list(@PathVariable("name") String afterName, @PathVariable("max") int maxResults) {
	return personService.findNextPage(afterName, maxResults);
    }
    
    @RequestMapping(value = "create/dummy", method = RequestMethod.POST)
    public void createDummy() {
	personService.create("Otto", 15);
    }
    
    @RequestMapping(value = "create/form", method = RequestMethod.POST, 
	    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void createForm(@RequestParam("name") String name, @RequestParam("age") int age) {
	personService.create(name, age);
    }
    
    @RequestMapping(value = "create", method = RequestMethod.POST, 
	    consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Person person) {
	personService.create(person.getName(), person.getAge());
    }
    
    @RequestMapping(value = "delete/{name}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable("name") String name) {
	personService.delete(name);
    }
}
