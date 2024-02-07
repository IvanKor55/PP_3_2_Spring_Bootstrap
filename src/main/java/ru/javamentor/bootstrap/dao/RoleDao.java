package ru.javamentor.bootstrap.dao;

import ru.javamentor.bootstrap.model.Role;

import java.util.List;


public interface RoleDao {

    void addRole(Role role);

    void deleteRole(Role role);

    Role getRole (Long id);

    Role getRoleByName (String authority);

    List<Role> getListRoles(Long id);

    List<Role> getAllRoles();
}
