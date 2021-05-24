package com.br.contact.contactList.enums;

public enum Type {

	EMAIL("email"),
	CELL("CELULAR"),
	TELEPHONE("telefone");
	
	private String label;

	private Type(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
