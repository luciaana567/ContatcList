package com.br.contact.contactList.service;

import java.util.List;
import java.util.Optional;

import com.br.contact.contactList.dto.PersonDTO;
import com.br.contact.contactList.model.Person;

public interface PersonService {

	List<Person> findAll();

	void save(Person person) throws Exception;

	Optional<Person> findById(long personId);
	
	boolean existsByCpf(String cpf);

	boolean verifyCpfs(Person person, PersonDTO dto);
}
