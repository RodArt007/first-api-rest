package com.std.mx.service;

import org.springframework.http.ResponseEntity;

import com.std.mx.model.dto.ClientDto;


public interface IClientService {
	
	ResponseEntity<?> save(ClientDto clientDto);
	
	ResponseEntity<?> update(ClientDto clientDto, Integer id);
	
	ResponseEntity<?> findById(Integer id);
	
	ResponseEntity<?> delete(Integer id);

}
