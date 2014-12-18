package com.codaconsultancy.dao;

import com.codaconsultancy.entities.Address;

public class AddressDAOImpl extends HibernateDAO<Address, Long> implements AddressDAO {

    public AddressDAOImpl() {
        super(Address.class);
    }
}
