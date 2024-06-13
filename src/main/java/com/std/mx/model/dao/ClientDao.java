package com.std.mx.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.std.mx.model.entity.Client;

public interface ClientDao extends CrudRepository<Client, Integer> {

}
