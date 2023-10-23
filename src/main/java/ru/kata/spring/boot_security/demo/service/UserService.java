package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user,List<Long> roleId);

    User getById(Long id);

    List<User> getAllUser();

    void updateUser(Long id, User user, List<Long> roleId);

    void deleteUser(Long id);

    void deleteUser(User user);

    List<User> findUser(User user);

    User getUserByUsername(String username);


}
