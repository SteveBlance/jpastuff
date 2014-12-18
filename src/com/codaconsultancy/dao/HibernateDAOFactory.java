package com.codaconsultancy.dao;

public class HibernateDAOFactory extends DAOFactory {

    @Override
    public AddressDAO newAddressDAO() {
        return new AddressDAOImpl();
    }

    @Override
    public ClientDAO newClientDAO() {
        return new ClientDAOImpl();
    }

    @Override
    public ClientDetailDAO newClientDetailDAO() {
        return new ClientDetailDAOImpl();
    }

    @Override
    public SkillDAO newSkillDAO() {
        return new SkillDAOImpl();
    }
}
