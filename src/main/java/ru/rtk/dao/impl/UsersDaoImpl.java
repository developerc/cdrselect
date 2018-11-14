package ru.rtk.dao.impl;

import ru.rtk.dao.UsersDao;
import ru.rtk.entity.Users;

import javax.persistence.Query;
import java.util.List;

public class UsersDaoImpl extends BasicDaoImpl<Users> implements UsersDao {
    public UsersDaoImpl(Class<Users> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Users> getUsersByLgn(String lgn) {
        Query query = getSessionFactory().createQuery("SELECT a FROM Users as a WHERE (a.login = :Lgn)");
        query.setParameter("Lgn", lgn);
        return query.getResultList();
    }

    @Override
    public List<Users> getAdminAll() {
        Query query = getSessionFactory().createQuery("SELECT a FROM Users as a WHERE (a.role_name = 'ROLE_ADMIN')");
//        query.setParameter("Role", 'ROLE_ADMIN');
        return query.getResultList();
    }
}
