package ru.javamentor.bootstrap.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.javamentor.bootstrap.dao.UserDao;
import ru.javamentor.bootstrap.model.Role;
import ru.javamentor.bootstrap.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void addUser(User user, String[] rolesList) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDao.addUser(user);
        List<Role> roles = Arrays.stream(rolesList)
                .map(roleId -> roleService.getRole(Long.parseLong(roleId)))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (!roles.isEmpty()) {
            user.setRoles(roles);
        }
    }

    @Transactional
    @Override
    public void editUser(User user, String[] rolesList) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        List<Role> roles = new ArrayList<>();
        for (String roleId : rolesList) {
            Role role = roleService.getRole(Long.parseLong(roleId));
            roles.add(role);
        }
        if (!roles.isEmpty()) {
            user.setRoles(roles);
        }
        userDao.editUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<User> getListUsers() {
        return userDao.getListUsers();
    }

    @Override
    public User getUser (Long id) {
        return userDao.getUser(id);
    }

    @Override
    public User findByLogin (String login) {
        return userDao.findByLogin(login);
    }
}