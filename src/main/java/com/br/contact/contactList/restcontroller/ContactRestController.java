package com.br.contact.contactList.restcontroller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.contact.contactList.dto.ContactDTO;
import com.br.contact.contactList.model.Contact;
import com.br.contact.contactList.model.Person;
import com.br.contact.contactList.utils.*;

@RestController
@RequestMapping("/api")
public class ContactRestController extends BaseRestController<ContactDTO>{

	@Autowired
	private ContactRestControllerImp contactRestControllerImp;
	
	@Autowired
	private PersonRestControllerImp personRestControllerImp;

	@Override
	@PostMapping("/contact")
	protected ResponseEntity<?> insert(@RequestBody ContactDTO dto) {
		try {
		if(contactRestControllerImp.exists(dto.value)) {
			return ResponseEntity.badRequest().body("Contato já cadastrado!");
		}else {
			// Realiza o parse para entity!
			Contact contact = contactRestControllerImp.convertContactDTOtoContact(dto);

			// Salva
			contactRestControllerImp.save(contact);
			return ResponseEntity.created(URI.create("SUCESS!")).build();
		}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	@DeleteMapping("/contact/{id}")
	protected ResponseEntity<?> delete(@PathVariable long id) {
		//deletar todos os contatos pertecentes aquele usuário
		try {
//			List<Contact> contacts = contactRestControllerImp.findByPersonId(id);
//			for(int i = 0; i < contacts.size(); i++) {
//				if (contacts.get(i) != null) {
//					contactRestControllerImp.delete(contacts.get(i));
//				}
//			}
		
			Optional<Contact> contact = contactRestControllerImp.findById(id);
			contactRestControllerImp.delete(contact.get());
			
			return ResponseEntity.ok().body("CONTACTS DELETE WITH SUCESS!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	@PutMapping("/contact/{id}")
	protected ResponseEntity<?> update(@RequestBody ContactDTO dto,@PathVariable long id) {
		try {

			Optional<Contact> contact = contactRestControllerImp.findById(id);

			if (contact.isPresent()) {
				if (!contactRestControllerImp.verifyValue(contact.get(), dto)) {
					Contact c = contactRestControllerImp.personPopulate(contact.get(),dto);
					contactRestControllerImp.save(c);
					return ResponseEntity.status(HttpStatus.OK).body("SUCESS!");
				}else {
					// Verifica se CPF existem cadastrados!
					if(contactRestControllerImp.exists(dto.value)) {
						return ResponseEntity.badRequest().body("Contato já cadastrado!");
					}
					Contact c = contactRestControllerImp.personPopulate(contact.get(),dto);
					contactRestControllerImp.save(c);
				}
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND!");
	}

	@Override
	@GetMapping("/contact/{id}")
	protected ResponseEntity<?> findById(@PathVariable long id) {
		try {
			Optional<Contact> contact = contactRestControllerImp.findById(id);
			if (contact.isPresent()) {
				ContactDTO contactDTO = contactRestControllerImp.convertContactTOContactDTO(contact.get());
				return ResponseEntity.status(HttpStatus.OK).body(contactDTO);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND!");
	}

	@Override
	@GetMapping("/contact")
	protected ResponseEntity<?> findAll() {
		return ResponseEntity.ok(contactRestControllerImp.findAll());
	}	
	
	
	@GetMapping("/contact/person/{personId}")
		protected ResponseEntity<?> findByPerson(@PathVariable long personId) {
		try {
			Optional<Person> person = personRestControllerImp.findById(personId);
	
			if (person.isPresent()) {
				return ResponseEntity.ok(contactRestControllerImp.findByPerson(person.get()));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND!");
	}	
}
