package com.br.contact.contactList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.contact.contactList.dto.PersonDTO;
import com.br.contact.contactList.model.Person;
import com.br.contact.contactList.repository.PersonRepository;

@Service
public class PersonServiceImp implements PersonService{
	
	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public void save(Person person) throws Exception {
		personRepository.save(person);
	}

	@Override
	public Optional<Person> findById(long personId) {
		return personRepository.findById(personId);
	}
	
	@Override
	public boolean verifyCpfs(Person person, PersonDTO dto) {
		if(person.getCpf() == dto.getCpf()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean existsByCpf(String cpf) {
		return personRepository.existsByCpf(cpf);
	}
	
}
