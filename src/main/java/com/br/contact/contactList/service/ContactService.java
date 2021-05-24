package com.br.contact.contactList.service;

import java.util.List;
import java.util.Optional;

import com.br.contact.contactList.dto.ContactDTO;
import com.br.contact.contactList.model.Contact;
import com.br.contact.contactList.model.Person;

public interface ContactService {

	List<Contact> findAll();

	void save(Contact contact) throws Exception;

	Optional<Contact> findById(long contactId);
	
	List<Contact> findByPerson(Person person);

	boolean existsByValue(String value);

	boolean verifyValue(Contact contact, ContactDTO dto);
	
	void delete(Contact contact);
}
