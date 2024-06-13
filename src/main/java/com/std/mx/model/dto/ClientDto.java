package com.std.mx.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.std.mx.model.entity.Client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idClient;
	private String name;
	private String lastName;
	private String email;
	private Date dateRegister;
	
	public static ClientDto dataClient(Client client) {
		return ClientDto.builder()
			.idClient(client.getIdClient())
			.name(client.getName())
			.lastName(client.getLastName())
			.email(client.getEmail())
			.dateRegister(client.getRegistrationDate())
			.build();
	}

}
