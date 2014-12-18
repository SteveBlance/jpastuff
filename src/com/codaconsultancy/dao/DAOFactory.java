package com.codaconsultancy.dao;

public abstract class DAOFactory {

    private static final Class FACTORY_CLASS = HibernateDAOFactory.class;
//    private static final Class FACTORY_CLASS =  JDBCDAOFactory.class;
//    private static final Class FACTORY_CLASS =  JDODAOFactory.class;

    public static DAOFactory getFactory() {
        try {
            return (DAOFactory) FACTORY_CLASS.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't create factory");
        }
    }

    public abstract AddressDAO newAddressDAO();

    public abstract ClientDAO newClientDAO();

    public abstract ClientDetailDAO newClientDetailDAO();

    public abstract SkillDAO newSkillDAO();

}
