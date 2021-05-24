package com.br.contact.contactList.mapper;

import com.br.contact.contactList.dto.ContactDTO;
import com.br.contact.contactList.model.Contact;

public class ContactMapper {

	private ContactMapper() {

	}

	public static ContactDTO toContactDTO(Contact contact) {
		return new ContactDTO(contact.getContactId(), contact.getValue(), PersonMapper.toPersonDTO(contact.getPerson()), contact.getContactType());
	}

	public static Contact toDTOContact(ContactDTO dto) {
		return new Contact(dto.id, dto.value, dto.contactType, PersonMapper.toDTOPerson(dto.person));
	}

}
