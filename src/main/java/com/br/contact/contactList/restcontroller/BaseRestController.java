package com.br.contact.contactList.restcontroller;

import org.springframework.http.ResponseEntity;

public abstract class BaseRestController<DTO> {

	protected abstract ResponseEntity<?> insert(DTO dto);

	protected abstract ResponseEntity<?> delete(long id);

	protected abstract ResponseEntity<?> update(DTO dto, long id);

	protected abstract ResponseEntity<?> findById(long id);

	protected abstract ResponseEntity<?> findAll();
}
