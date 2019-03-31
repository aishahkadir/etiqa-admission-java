package com.journaldev.spring.dao;

import java.util.List;

import javax.management.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addStudent(int courseid) {
		     
		Session session = this.sessionFactory.getCurrentSession();
		List<Person> studentsList = session.createQuery("from person where person.courseid = :courseid").setParameter("courseid", courseid).list();
		
		/*session.get(Person.class, courseid);
		logger.info("Person saved successfully, Person Details="+courseid);*/
		
	}

	@Override
	public void addPerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Person saved successfully, Person Details="+p);
	}


	@Override
	public void updatePerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Person updated successfully, Person Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> listPersons() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Person> personsList = session.createQuery("from Person").list();
		/*Criteria personsListcid = session.createCriteria(Person.class);
		Object courseid;
		personsListcid.add(Restriction.ge("courseid",courseid));*/
		
		for(Person p : personsList){
			logger.info("Person List::"+p);
		}
		return personsList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> listStudents(int courseid) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Person> studentsList = session.createQuery("from Person p where p.courseid = :courseid").setParameter("courseid", courseid).list();
		/*return (List<Person>) sessionFactory.getCurrentSession().get(
                Person.class, courseid);*/
		
		return studentsList;
	}
	
	@Override
	public Person getPersonByCourseId(int courseid) {
		Session session = this.sessionFactory.getCurrentSession();		
		Person p = (Person) session.load(Person.class, new Integer(courseid));
		logger.info("Person loaded successfully, Person details="+p);
		return p;
	}


	@Override
	public Person getPersonById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Person p = (Person) session.load(Person.class, new Integer(id));
		logger.info("Person loaded successfully, Person details="+p);
		return p;
	}

	@Override
	public void removePerson(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Person p = (Person) session.load(Person.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Person deleted successfully, person details="+p);
	}

}
