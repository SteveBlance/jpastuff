package com.codaconsultancy.dao;

import com.codaconsultancy.model.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import java.io.Serializable;
import java.util.List;

public abstract class HibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private Class<T> persistentClass;

    public HibernateDAO(Class c) {
        this.persistentClass = c;
    }

    @Override
    public T findByPrimaryKey(ID id) {
        HibernateUtil.getSession().load(persistentClass, id);
        return null;
    }

    @Override
    public List<T> findAll(int startIndex, int fetchSize) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(persistentClass);
        criteria.setFirstResult(startIndex);
        criteria.setMaxResults(fetchSize);
        return criteria.list();
    }

    @Override
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(persistentClass);
        Example example = Example.create(exampleInstance);
        if (excludeProperty != null) {
            for (String property : excludeProperty) {
                example.excludeProperty(property);
            }
            criteria.add(example);
        }
        return criteria.list();
    }

    @Override
    public T save(T entity) {
        HibernateUtil.getSession().save(entity);
        return entity;
    }

    @Override
    public void delete(T entity) {
        HibernateUtil.getSession().delete(entity);
    }

    @Override
    public void beginTransaction() {
        HibernateUtil.beginTransaction();
    }

    @Override
    public void commitTransaction() {
        HibernateUtil.commitTransaction();
    }
}
