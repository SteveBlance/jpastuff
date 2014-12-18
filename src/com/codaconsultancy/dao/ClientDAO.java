package com.codaconsultancy.dao;

import com.codaconsultancy.entities.Client;

import java.util.List;

public interface ClientDAO extends GenericDAO<Client, Long> {

    List<Client> findAllVerified();
}
