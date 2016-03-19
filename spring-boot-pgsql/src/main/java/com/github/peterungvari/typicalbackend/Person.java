package com.github.peterungvari.typicalbackend;

/**
 *
 * @author jupi
 */
public class Person {
    
    private String name;
    private int age;
    private Person mother;
    private Person father;
    private Person[] siblings;
    private Person[] children;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getAge() {
	return age;
    }

    public void setAge(int age) {
	this.age = age;
    }

    public Person getMother() {
	return mother;
    }

    public void setMother(Person mother) {
	this.mother = mother;
    }

    public Person getFather() {
	return father;
    }

    public void setFather(Person father) {
	this.father = father;
    }

    public Person[] getSiblings() {
	return siblings;
    }

    public void setSiblings(Person[] siblings) {
	this.siblings = siblings;
    }

    public Person[] getChildren() {
	return children;
    }

    public void setChildren(Person[] children) {
	this.children = children;
    }
    
    
}
