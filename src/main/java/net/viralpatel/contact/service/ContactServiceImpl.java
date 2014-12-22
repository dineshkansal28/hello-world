package net.viralpatel.contact.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.viralpatel.contact.dao.ContactDAO;
import net.viralpatel.contact.form.Contact;

@Service
public class ContactServiceImpl implements ContactService {
	
	private static Logger logger= Logger.getLogger(ContactServiceImpl.class);

	@Autowired
	private ContactDAO contactDAO;
	
	@Transactional
	public void addContact(Contact contact) {
		logger.info("Enter addContact method in Service Layer");
		contactDAO.addContact(contact);
		logger.info("Exit addContact method in Service Layer");
	}

	@Transactional(readOnly = true)
	public List<Contact> listContact() {

		return contactDAO.listContact();
	}

	@Transactional
	public void removeContact(Integer id) {
		contactDAO.removeContact(id);
	}
	
	@Transactional
	public void editContact(Contact contact) {
		//contactDAO.removeContact(id);
		contactDAO.editContact(contact);
	}
	
	@Transactional(readOnly = true)
	public Contact getContactById(Integer id){
		return contactDAO.getContactById(id);
	}
	
	@Transactional(readOnly = true)
	public Contact getRecentAddedContact(){
		
		return contactDAO.getRecentAddedContact();
	}
}
