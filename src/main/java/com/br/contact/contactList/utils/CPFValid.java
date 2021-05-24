package com.br.contact.contactList.utils;

import br.com.caelum.stella.validation.CPFValidator;

public class CPFValid {

	public static void cpfValid(String cpf) {
		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.assertValid(cpf);
	}
}
