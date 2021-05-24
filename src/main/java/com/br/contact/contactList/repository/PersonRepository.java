package com.br.contact.contactList.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import com.br.contact.contactList.model.Contact;
import com.br.contact.contactList.model.Person;

public interface PersonRepository extends JpaRepositoryImplementation<Person, Long> {

	@Query("SELECT p FROM Person p")
	List<Person> findAll();

	boolean existsByCpf(String cpf);
	
	Optional<Person> findById(Long personId);

}
