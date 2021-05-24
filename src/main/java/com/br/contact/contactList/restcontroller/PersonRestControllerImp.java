package com.br.contact.contactList.restcontroller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.contact.contactList.dto.PersonDTO;
import com.br.contact.contactList.model.Person;
import com.br.contact.contactList.service.PersonService;
import com.br.contact.contactList.mapper.PersonMapper;

@Service
public class PersonRestControllerImp {
	
	@Autowired
	private PersonService personService;

	public  List<PersonDTO> findAll() {
		return personService.findAll().stream().map(PersonMapper::toPersonDTO).collect(Collectors.toList());
	}

	public void save(Person person) throws Exception {
		personService.save(person);
	}

	public Person convertPersonDTOTOPerson(PersonDTO dto) throws Exception{
		return PersonMapper.toDTOPerson(dto);
	}
	
	public PersonDTO convertPersonTOPersonDTO(Person p) throws Exception{
		return PersonMapper.toPersonDTO(p);
	}

	public Optional<Person> findById(long id) {
		return personService.findById(id);
	}

	public Person personPopulate(Person person, PersonDTO dto) {
		person.setCpf(dto.cpf);
		person.setDate(dto.date);
		person.setName(dto.name);
		person.setLastName(dto.lastName);
		return person;
	}


	public Optional<Person> findBy(long id) {
		return personService.findById(id);
	}


	public boolean verifyCpfs(Person person, PersonDTO dto) {
		if(person.getCpf() == dto.getCpf()) {
			return true;
		}
		return false;
	}
	
	public boolean exists(String cpf) throws Exception{
		if(personService.existsByCpf(cpf)) {
			return true;
		}
		return false;
	}	
}

