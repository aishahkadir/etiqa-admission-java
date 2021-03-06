package com.journaldev.spring.service;

import java.util.List;

import com.journaldev.spring.model.Person;

public interface PersonService {

	public void addPerson(Person p);
	public void addStudent(int courseid);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public List<Person> listStudents(int courseid);
	public Person getPersonById(int id);
	public Person getPersonByCourseId(int courseid);
	public void removePerson(int id);
	
}
