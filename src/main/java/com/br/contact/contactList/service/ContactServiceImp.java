package com.br.contact.contactList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.contact.contactList.dto.ContactDTO;
import com.br.contact.contactList.model.Contact;
import com.br.contact.contactList.model.Person;
import com.br.contact.contactList.repository.ContactRepository;

@Service
public class ContactServiceImp implements ContactService{
	
	@Autowired
	private ContactRepository ContactRepository;

	@Override
	public List<Contact> findAll() {
		return ContactRepository.findAll();
	}
	
	@Override
	public List<Contact> findByPerson(Person person) {
		return ContactRepository.findByPerson(person);
	}

	@Override
	public void save(Contact contact) throws Exception {
		ContactRepository.save(contact);
	}

	@Override
	public Optional<Contact> findById(long ContactId) {
		return ContactRepository.findById(ContactId);
	}
	
	@Override
	public boolean verifyValue(Contact contact, ContactDTO dto) {
		if(contact.getValue() == dto.getValue()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean existsByValue(String value) {
		return ContactRepository.existsByValue(value);
	}
	
	@Override
	public void delete(Contact contact) {
		ContactRepository.delete(contact);
	}

}
