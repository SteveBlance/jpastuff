package com.codaconsultancy.dao;

import com.codaconsultancy.entities.Skill;

public class SkillDAOImpl extends HibernateDAO<Skill, Long> implements SkillDAO {

    public SkillDAOImpl() {
        super(Skill.class);
    }
}
