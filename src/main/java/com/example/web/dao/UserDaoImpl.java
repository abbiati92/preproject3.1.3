package com.example.web.dao;

import com.example.web.model.Role;
import com.example.web.model.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getUsersList() {
        return entityManager.createQuery("SELECT u FROM User u",User.class).getResultList();
    }

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.find(User.class,id));
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findByUsername(String username) {
        Query query = entityManager.createQuery
                ("select u from User u left join fetch u.roles where u.userName=:username", User.class);
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }

   @Override
    public void setUserRole(Long id, int roleId) {
        entityManager.createNativeQuery("INSERT INTO user_roles (users_id, roles_id) VALUES (?,?)")
                .setParameter(1, id)
                .setParameter(2, roleId)
                .executeUpdate();
    }
}
