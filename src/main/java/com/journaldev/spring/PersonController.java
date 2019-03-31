package com.journaldev.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.service.PersonService;

@Controller
public class PersonController {
	
	private PersonService personService;
	
	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(PersonService ps){
		this.personService = ps;
	}
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String listPersons(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("listPersons", this.personService.listPersons());
		return "person";
	}
	
	//For add and update person both
	@RequestMapping(value= "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person p){
		
		if(p.getId() == 0){
			//new person, add it
			this.personService.addPerson(p);
		}else{
			//existing person, call update
			this.personService.updatePerson(p);
		}
		
		return "redirect:/persons";
		
	}
	
	/*@RequestMapping(value= "/person/search/{courseid}")
	public String listStudents(@PathVariable("courseid") int courseid, Model model){
		
		model.addAttribute("person", this.personService.getPersonByCourseId(courseid));
        model.addAttribute("listStudents", this.personService.listStudents(courseid));
        return "person";
	   
		
	}*/
	
	/*@RequestMapping(value= "/person/search", method = RequestMethod.POST)
	public String listStudents(@PathVariable("courseid") int courseid,Model model){
		
		 model.addAttribute("person", this.personService.getPersonByCourseId(courseid));
	     model.addAttribute("listStudents", this.personService.listStudents(courseid));
	     return "redirect:/persons";
	   
		
	}*/
	
	
	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
		
        this.personService.removePerson(id);
        return "redirect:/persons";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }
    
    @RequestMapping(value= "/person/{courseid}", method = RequestMethod.POST)
	public String searchStudents(@RequestParam("courseid") int courseid, Model model,@ModelAttribute("person") Person p){
		
    	if(p.getCourseid() != 0){
    		
    		this.personService.listStudents(courseid);
    		
    		/*this.personService.getPersonByCourseId(courseid);
    		model.addAttribute("person", this.personService.getPersonByCourseId(courseid));
    		model.addAttribute("listStudents", this.personService.listStudents(courseid));
            /*model.addAttribute("listStudents", this.personService.listStudents(courseid));*/
              	   
			/*this.personService.addStudent(p);*/
		}
    	
    	else{
    		model.addAttribute("person", this.personService.getPersonByCourseId(courseid));
            model.addAttribute("listPersons", this.personService.listPersons());
		}
    	
    	 return "redirect:/persons";
		
		
	}
	
}
