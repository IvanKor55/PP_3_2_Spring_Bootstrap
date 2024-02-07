package ru.javamentor.bootstrap.service;
import ru.javamentor.bootstrap.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user, String[] rolesList);

    void editUser(User user, String[] rolesList);

    void deleteUser(Long id);

    List<User> getListUsers();

    User getUser (Long id);

    User findByLogin (String login);
}
