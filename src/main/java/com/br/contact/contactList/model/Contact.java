package com.br.contact.contactList.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.br.contact.contactList.enums.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Contact")

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter @Setter

public class Contact {
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Id
    private Long contactId;
	
	@Column
    private String value;
	
	@Enumerated(EnumType.STRING)
	private Type contactType;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "personId")
	private Person person;
}
