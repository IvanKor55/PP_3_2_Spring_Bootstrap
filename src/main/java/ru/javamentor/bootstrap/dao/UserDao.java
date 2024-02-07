package ru.javamentor.bootstrap.dao;

import ru.javamentor.bootstrap.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    void editUser(User user);

    void deleteUser(Long id);

    List<User> getListUsers();

    User getUser (Long id);

    User findByLogin (String login);
}
