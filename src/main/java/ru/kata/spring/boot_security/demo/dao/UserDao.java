package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    User getById(Long id);

    List<User> getAllUser();

    void updateUser(Long id, User user);

    void deleteUser(Long id);

    void deleteUser(User user);

    List<User> findUser(User user);

    User getUserByUsername(String username);
}
