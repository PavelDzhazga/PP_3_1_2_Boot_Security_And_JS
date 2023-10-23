package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.createQuery("delete User where id =: id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void deleteUser(User user) {
        entityManager.joinTransaction();
        findUser(user).forEach(x -> entityManager.remove(x.getId()));
    }

    @Override
    public List<User> findUser(User user) {
        TypedQuery<User> uQuery = entityManager.createQuery("from User where email =: email", User.class)
                .setParameter("email", user.getEmail());
        return uQuery.getResultList();
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery("from User where email =: username", User.class)
                .setParameter("username", username).getSingleResult();
    }

    @Override
    public List<User> getAllUser() {
        TypedQuery<User> typedQuery = entityManager.createQuery("FROM User", User.class);
        return typedQuery.getResultList();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);

    }

    @Override
    public User getById(Long id) {
        TypedQuery<User> typedQuery = entityManager.createQuery("from User where id =: id", User.class)
                .setParameter("id", id);
        return typedQuery.getSingleResult();
    }
}
