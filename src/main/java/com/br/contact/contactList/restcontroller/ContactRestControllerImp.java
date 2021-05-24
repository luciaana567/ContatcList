package com.br.contact.contactList.restcontroller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.contact.contactList.dto.ContactDTO;
import com.br.contact.contactList.model.Contact;
import com.br.contact.contactList.model.Person;
import com.br.contact.contactList.service.ContactService;
import com.br.contact.contactList.mapper.ContactMapper;

@Service
public class ContactRestControllerImp {
	
	@Autowired
	private ContactService ContactService;

	public  List<ContactDTO> findAll() {
		return ContactService.findAll().stream().map(ContactMapper::toContactDTO).collect(Collectors.toList());
	}

	public void save(Contact contact) throws Exception {
		ContactService.save(contact);
	}

	public Contact convertContactDTOtoContact(ContactDTO dto) throws Exception{
		return ContactMapper.toDTOContact(dto);
	}
	
	public ContactDTO convertContactTOContactDTO(Contact p) throws Exception{
		return ContactMapper.toContactDTO(p);
	}

	public Optional<Contact> findById(long id) {
		return ContactService.findById(id);
	}

	public Contact personPopulate(Contact contact, ContactDTO dto) {
		Contact c = ContactMapper.toDTOContact(dto);
		contact.setContactType(dto.contactType);
		contact.setValue(dto.value);
		contact.setPerson( c.getPerson());
		return contact;
	}


	public List<Contact> findByPerson(Person person) {
		return ContactService.findByPerson(person);
	}


	public boolean verifyValue(Contact contact, ContactDTO dto) {
		if(contact.getValue() == dto.getValue()) {
			return true;
		}
		return false;
	}
	
	public boolean exists(String value) throws Exception{
		if(ContactService.existsByValue(value)) {
			return true;
		}
		return false;
	}	
	
	public void delete(Contact contact) {
		ContactService.delete(contact);
	}
}

