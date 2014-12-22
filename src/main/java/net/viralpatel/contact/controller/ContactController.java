package net.viralpatel.contact.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.viralpatel.contact.form.Contact;
import net.viralpatel.contact.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping("/index")
	public String listContacts(Map<String, Object> map) {

		map.put("contact", new Contact());
		List<Contact> list = contactService.listContact();
	
		map.put("contactList", list);

		return "contact";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("contact")
	Contact contact, BindingResult result) {
		
		contactService.addContact(contact);

		return "redirect:/index";
	}

	@RequestMapping("/delete/{contactId}")
	public String deleteContact(@PathVariable("contactId")
	Integer contactId) {

		contactService.removeContact(contactId);

		return "redirect:/index";
	}
	
	@RequestMapping("/edit/{contactId}")
	public String editContact(Model map,@PathVariable("contactId") Integer contactId){
		
		//Map<String, Object> map =new HashMap<String, Object>();
		Contact contact = new Contact();
		contact=contactService.getContactById(contactId);
		map.addAttribute("contact", contact);
		//map.put("contact", contact);
		
		return "editcontact";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editContact(@ModelAttribute("contact")
	Contact contact, BindingResult result) {
		
		contactService.editContact(contact);

		return "redirect:/index";
	}
}
