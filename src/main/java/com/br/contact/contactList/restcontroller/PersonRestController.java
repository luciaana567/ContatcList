package com.br.contact.contactList.restcontroller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.contact.contactList.dto.PersonDTO;
import com.br.contact.contactList.model.Person;
import com.br.contact.contactList.utils.*;

@RestController
@RequestMapping("/api")
public class PersonRestController extends BaseRestController<PersonDTO>{

	@Autowired
	private PersonRestControllerImp personRestControllerImp;

	@PostMapping("/person")
	protected ResponseEntity<?> insert(@RequestBody PersonDTO dto) {
		try {
			//valida cpf
			CPFValid.cpfValid(dto.cpf);

		if (!personRestControllerImp.exists(dto.getCpf())) {
			Person person = personRestControllerImp.convertPersonDTOTOPerson(dto);

			// Salva
			personRestControllerImp.save(person);
			return ResponseEntity.created(URI.create("SUCESS!")).build();
				
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND!");
	}
	

	@Override
	protected ResponseEntity<?> delete(@PathVariable long id) {
		return null;
	}

	@Override
	@PutMapping("/person/{id}")
	protected ResponseEntity<?> update(@RequestBody PersonDTO dto,@PathVariable long id) {
		try {
			//valida cpf
			CPFValid.cpfValid(dto.cpf);

			Optional<Person> person = personRestControllerImp.findById(id);

			if (person.isPresent()) {
				if (!personRestControllerImp.verifyCpfs(person.get(), dto)) {
					Person p = personRestControllerImp.personPopulate(person.get(),dto);
					personRestControllerImp.save(p);
					return ResponseEntity.status(HttpStatus.OK).body("SUCESS!");
				}else {
					// Verifica se CPF existem cadastrados!
					if (personRestControllerImp.exists(dto.cpf)) {
						return ResponseEntity.badRequest().body("CPF já cadastrado!");
					}
					Person p = personRestControllerImp.personPopulate(person.get(),dto);
					personRestControllerImp.save(p);
				}
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND!");
	}

	@Override
	@GetMapping("/person/{id}")
	protected ResponseEntity<?> findById(@PathVariable long id) {
		try {
			Optional<Person> person = personRestControllerImp.findById(id);
			if (person.isPresent()) {
				PersonDTO personDTO = personRestControllerImp.convertPersonTOPersonDTO(person.get());
				return ResponseEntity.status(HttpStatus.OK).body(personDTO);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND!");
	}

	@Override
	@GetMapping("/person")
	protected ResponseEntity<?> findAll() {
		return ResponseEntity.ok(personRestControllerImp.findAll());
	}	
	
}
