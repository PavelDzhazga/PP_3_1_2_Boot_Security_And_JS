package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("Select role From Role role").getResultList();
    }

    @Override
    public Role getRole(String username) {
        return entityManager.createQuery("select role from Role role where role.role =: role", Role.class)
                .setParameter("role", username).getSingleResult();
    }

    @Override
    public Role getRoleById(Long id) {
        return (Role) entityManager.createQuery("select role from Role role where role.id =: id")
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
