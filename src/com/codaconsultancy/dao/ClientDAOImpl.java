package com.codaconsultancy.dao;

import com.codaconsultancy.entities.Client;

import java.util.List;

public class ClientDAOImpl extends HibernateDAO<Client, Long> implements ClientDAO {

    public ClientDAOImpl() {
        super(Client.class);
    }

    @Override
    public List<Client> findAllVerified() {
        Client client = new Client();
        client.setVerified(true);
        return findByExample(client, null);
    }
}
