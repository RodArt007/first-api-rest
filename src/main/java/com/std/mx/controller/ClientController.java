package com.std.mx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.std.mx.model.dto.ClientDto;
import com.std.mx.service.IClientService;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
	
	@Autowired
	private IClientService clientService;
	
	@PostMapping("client")
	public ResponseEntity<?> create(@RequestBody ClientDto clientDto){
		System.out.println("Saving client.");
		
		return clientService.save(clientDto);
		
	}
	
	@GetMapping("/client/{id}")
	public ResponseEntity<?> showById(@PathVariable Integer id) {
		System.out.println("Searching client by id: "+id);
		
		return clientService.findById(id);
		
	}
	
	@PutMapping("client/{id}")
	public ResponseEntity<?> update(@RequestBody ClientDto clientDto, @PathVariable Integer id){
		System.out.println("Updating client with id: "+ id);
		
		return clientService.update(clientDto, id);
	}
	
	
	@DeleteMapping("client/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		System.out.println("Deleting client id: "+id);
		
		return clientService.delete(id);
	}

}
