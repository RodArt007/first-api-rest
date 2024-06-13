package com.std.mx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.mx.model.dao.ClientDao;
import com.std.mx.model.dto.ClientDto;
import com.std.mx.model.entity.Client;
import com.std.mx.payload.ManagerResponseEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImp implements IClientService {

	@Autowired
	private ClientDao clientDao;
	
	@Transactional
	@Override
	public ResponseEntity<?> save(ClientDto clientDto) {
		Client registeredClient = null;
		
		try {
			registeredClient = clientDao.save(convertDtoToClient(clientDto));
			
			return new ResponseEntity<>(ManagerResponseEntity.builder()
						.message("Client stored correctly!")
						.object(ClientDto.dataClient(registeredClient))
						.build(),
					HttpStatus.CREATED);
			
		} catch(DataAccessException exDt) {
			return new ResponseEntity<>(ManagerResponseEntity.builder()
					.message(exDt.getMessage())
					.object(null)
					.build(),
					HttpStatus.METHOD_NOT_ALLOWED);
		}
		
	}	
	
	@Transactional(readOnly = true)
	@Override
	public ResponseEntity<?> findById(Integer id) {
		//System.out.println("Entrando al service...");
		
		Client foundClient = clientDao.findById(id).orElse(null);
		
		if(foundClient == null) {
			return new ResponseEntity<>(
					ManagerResponseEntity.builder()
					.message("Register not found.")
					.object(null)
					.build(),
					HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(
					ClientDto.dataClient(foundClient), 
					HttpStatus.OK);
		}
	}
	
	@Transactional
	@Override
	public ResponseEntity<?> update(ClientDto clientDto, Integer id){
		Client updatedClient = null;
		
		try {			
			
			if(clientDao.existsById(id)) {
				clientDto.setIdClient(id);				
				updatedClient = clientDao.save(convertDtoToClient(clientDto));
				
				return new ResponseEntity<>(ManagerResponseEntity.builder()
							.message("Client updated correctly!")
							.object(ClientDto.dataClient(updatedClient))
							.build(),
						HttpStatus.CREATED);
				
			} else {
				return new ResponseEntity<>(
						ManagerResponseEntity.builder()
						.message("The register you are trying to update is not in DB.")
						.object(null)
						.build(),
						HttpStatus.NOT_FOUND);
			}			
			
		} catch(DataAccessException exDt) {
			return new ResponseEntity<>(ManagerResponseEntity.builder()
					.message(exDt.getMessage())
					.object(null)
					.build(),
					HttpStatus.METHOD_NOT_ALLOWED);
		}
		
	}
	
	@Transactional
	@Override
	public ResponseEntity<?> delete(Integer id){
		
		try {
						
			if(clientDao.existsById(id)) {
				Client deletedClient = clientDao.findById(id).orElseGet(null);
				clientDao.delete(deletedClient);
				
				return new ResponseEntity<>(ManagerResponseEntity.builder()
						.message("Client deleted correctly!")
						.object(null)
						.build(), 
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						ManagerResponseEntity.builder()
						.message("The register you are trying to delete is not in DB.")
						.object(null)
						.build(),
						HttpStatus.NOT_FOUND);
			}
			
		} catch(DataAccessException exDt) {
			return new ResponseEntity<>(ManagerResponseEntity.builder()
					.message(exDt.getMessage())
					.object(null)
					.build(),
					HttpStatus.METHOD_NOT_ALLOWED);
		} 
	}

	
	private Client convertDtoToClient(ClientDto clientDto) {
		Client client = Client.builder()
				.idClient(clientDto.getIdClient())
				.name(clientDto.getName())
				.lastName(clientDto.getLastName())
				.email(clientDto.getEmail())
				.registrationDate(clientDto.getDateRegister())
				.build();
		
		return clientDao.save(client);
	}


}
