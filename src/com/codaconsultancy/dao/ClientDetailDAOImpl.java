package com.codaconsultancy.dao;

import com.codaconsultancy.entities.ClientDetail;

public class ClientDetailDAOImpl extends HibernateDAO<ClientDetail, Long> implements ClientDetailDAO {

    public ClientDetailDAOImpl() {
        super(ClientDetail.class);
    }
}
