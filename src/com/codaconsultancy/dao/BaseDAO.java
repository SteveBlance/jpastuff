package com.codaconsultancy.dao;

import com.codaconsultancy.model.HibernateUtil;
import org.hibernate.Session;

public class BaseDAO {

    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    protected void save(Object entity) {
        Session session = this.getSession();
        session.saveOrUpdate(entity);
    }

    protected void delete(Object entity) {
        Session session = this.getSession();
        session.delete(entity);
    }

    protected Object findByPrimaryKey(Class c, Long primaryKey) {
        Session session = this.getSession();
        return session.get(c, primaryKey);
    }

}
