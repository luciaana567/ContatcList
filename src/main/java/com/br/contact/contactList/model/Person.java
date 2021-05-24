package com.br.contact.contactList.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Person")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter @Setter

public class Person{
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Id
	@Column
    private Long personId;
	
	@Column
    private String name;
	
	@Column
    private String lastName;
	
	@Column
    private String cpf;
	
	@Column
	private Date date;
	
}
