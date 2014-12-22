package net.viralpatel.contact.dao;

import java.util.Iterator;
import java.util.List;

import net.viralpatel.contact.form.Address;
import net.viralpatel.contact.form.Contact;
import net.viralpatel.contact.service.ContactServiceImpl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDAOImpl implements ContactDAO {
	
	private static Logger logger= Logger.getLogger(ContactDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void addContact(Contact contact) {
		if(logger.isDebugEnabled()){
		logger.debug("Enter addContact method in Dao Layer");
		}
		Integer contactId = (Integer) sessionFactory.getCurrentSession().save(contact);
		if(logger.isDebugEnabled()){
		logger.debug("added contactId is --"+contactId);
		}
		contact.setId(contactId);
		List<Address> list = contact.getAddressList();
		Iterator<Address> itr = list.iterator();
		while(itr.hasNext())
		{
			Address addr = itr.next();
			addr.setContact(contact);
			sessionFactory.getCurrentSession().save(addr);
		}
		if(logger.isDebugEnabled()){
		logger.debug("Exit addContact method in Dao Layer");
		}
	}

	public List<Contact> listContact() {

		Session session = sessionFactory.getCurrentSession();
		Transaction tran = session.beginTransaction();
		try{
			SQLQuery query =  session.createSQLQuery("Select * from Contacts").addEntity(Contact.class);
			List<Contact> list = query.list();
			for(Contact cont:list)
			{
				List<Address> adrrlist = cont.getAddressList();
				for(Address addr: adrrlist)
				{
					System.out.println(addr.getHouseNo());
				}

			}
			return list;
		}catch(Exception ex)
		{
			ex.printStackTrace();

		}finally{

		}
		return null;
	}

	public void removeContact(Integer id) {
		Contact contact = (Contact) sessionFactory.getCurrentSession().load(
				Contact.class, id);
		if (null != contact) {

			List<Address> addressList = contact.getAddressList();
			Iterator<Address> itr = addressList.iterator();
			while (itr.hasNext()){
				Address addr = itr.next();
				sessionFactory.getCurrentSession().delete(addr);
			}

			sessionFactory.getCurrentSession().delete(contact);
		}

	}
	
	
	public void editContact(Contact contact) {
		Integer contactId = contact.getId();
		Contact contactOld= getContactById(contactId);
		List<Address> oldAddressList = contactOld.getAddressList();
		Iterator<Address> itrOld = oldAddressList.iterator();
		while(itrOld.hasNext())
		{
			Address addrOld = itrOld.next();
			sessionFactory.getCurrentSession().delete(addrOld);
		}
		
		List <Address> newAddressList = contact.getAddressList();
		Iterator<Address> itrNew =newAddressList.iterator();
		while(itrNew.hasNext()){
			
			Address addrNew = itrNew.next();
			addrNew.setContact(contact);
			sessionFactory.getCurrentSession().save(addrNew);
		}
		
		 sessionFactory.getCurrentSession().merge(contact);
		 
	}
	
	public Contact getContactById(Integer id) {
		Contact contact = (Contact) sessionFactory.getCurrentSession().get(
				Contact.class, id);
		if (null != contact) {
			List<Address> addressList = contact.getAddressList();
			Iterator<Address> itr = addressList.iterator();
			while (itr.hasNext()){
				Address addr = itr.next();
			}
		}
		return contact;

	}
	
	public Contact getRecentAddedContact(){
		Session session =sessionFactory.getCurrentSession();
		//Transaction tran = session.beginTransaction();
		try{
			/*SQLQuery sqlQuery =session.createSQLQuery("select * from Contacts where id in (select max(id) from Contacts)").addEntity(Contact.class);
			List<Contact> list = sqlQuery.list();
			Contact cnt =list.get(0);
			List<Address> adrrlist = cnt.getAddressList();*/
			
			DetachedCriteria maxId = DetachedCriteria.forClass(Contact.class).setProjection(Projections.max("id"));
			Criteria crt =session.createCriteria(Contact.class).add(Property.forName("id").eq(maxId));
			List<Contact> list = crt.list();
			Contact cnt =list.get(0);
			List<Address> adrrlist = cnt.getAddressList();
			
			return cnt;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
