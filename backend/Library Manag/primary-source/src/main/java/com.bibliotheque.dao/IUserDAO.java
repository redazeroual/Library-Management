package com.bibliotheque.dao;

import com.bibliotheque.model.User;

import java.util.List;

public interface IUserDAO {
    User authenticate(String username, String password);


    boolean existsByUsername(String username);

    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id);
    User getUserById(int id);
    List<User> getAllUsers();
}
