package com.progress.bootTest.dao;

import com.progress.bootTest.model.Contact;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;




/**
 * Defines DAO operations for the contact model.
 * @author www.codejava.net
 *
 */
@RepositoryRestResource(path = "phonebook")
public interface ContactDAO extends CrudRepository<Contact, Integer>{
	/*
	public void saveOrUpdate(Contact contact);
     
	public void delete(int contactId);
	
	public Contact get(int contactId);
	
	public List<Contact> list();
*/
}
