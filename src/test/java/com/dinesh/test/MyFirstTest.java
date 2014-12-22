package com.dinesh.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.viralpatel.contact.form.Address;
import net.viralpatel.contact.form.Contact;
import net.viralpatel.contact.service.ContactService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-servlet.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class MyFirstTest {
	
	@Autowired
	private ContactService contactService;

	@Test
	public void testAddContact() {
		
		Contact cnt = new Contact();
		Address adr = new Address();
		
		adr.setCity("Aagra");
		adr.setHouseNo("111");
		adr.setLocality("TajMahal");
		adr.setState("UP");
		adr.setZipCode("130120");
		
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(adr);
		
		cnt.setFirstname("Shahanzah2");
		cnt.setLastname("Azam2");
		cnt.setEmail("shah@azam.com");
		cnt.setTelephone("99101010");
		cnt.setAddressList(addressList);
		
		
		System.out.println("Number of contact in DB before Adding new contact-"+contactService.listContact().size());
		
		
		contactService.addContact(cnt);
		
		System.out.println("-----------------Contact Added--------------");
		
		int size = contactService.listContact().size();
		Assert.notNull(contactService.listContact());
		
		System.out.println("Number of contact in DB after Adding new contact-"+size);
		
		/*Contact contactToRemove=contactService.getRecentAddedContact();
		
		
		System.out.println("-----------------Contact removed--------------"+contactToRemove.getId());
		contactService.removeContact(contactToRemove.getId());
		Assert.isNull(contactService.getContactById(contactToRemove.getId()));*/
		
		
		
	}

}
