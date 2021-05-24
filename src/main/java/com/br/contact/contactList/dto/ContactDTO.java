package com.br.contact.contactList.dto;

import java.io.Serializable;

import com.br.contact.contactList.enums.Type;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDTO implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -6008257569245452387L;
	
	@JsonProperty("ContactId")
    public Long id;
	@JsonProperty("value")
	public String value;
	@JsonProperty("person")
	public PersonDTO person;
	@JsonProperty("contactType")
	public Type contactType;

}
