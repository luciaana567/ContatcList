package com.br.contact.contactList.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5507441283799204900L;
	@JsonProperty("personId")
    public Long id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("cpf")
    public String cpf;
    @JsonProperty("date")
    public Date date;
}
