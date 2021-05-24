package com.br.contact.contactList.mapper;

import com.br.contact.contactList.model.Person;
import com.br.contact.contactList.dto.PersonDTO;

public class PersonMapper {

	private PersonMapper() {

	}

	public static PersonDTO toPersonDTO(Person p) {
		return new PersonDTO(p.getPersonId(), p.getName(), p.getLastName(), p.getCpf(), p.getDate());
	}

	public static Person toDTOPerson(PersonDTO dto) {
		return new Person(dto.getId(), dto.getName(), dto.getLastName(), dto.getCpf(), dto.getDate());
	}

}
