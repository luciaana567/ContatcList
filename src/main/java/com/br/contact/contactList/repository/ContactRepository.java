package com.br.contact.contactList.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import com.br.contact.contactList.model.*;

public interface ContactRepository extends JpaRepositoryImplementation<Contact, Long> {

	boolean existsByValue(String value);
	
	Optional<Contact> findById(Long contactId);

	@Query("SELECT c FROM Contact c")
	List<Contact> findAll();

	List<Contact> findByPerson(Person person);
	
	@Query("SELECT c FROM Contact c WHERE c.contactType = :contactType")
	List<Contact> findByType(@Param("contactType")String contactType);

	@Query("SELECT c FROM Contact c WHERE c.value = :value")
	Optional<Contact> findByValue(@Param("value")String value);
}
