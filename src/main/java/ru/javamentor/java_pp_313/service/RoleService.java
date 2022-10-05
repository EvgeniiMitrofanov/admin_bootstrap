package ru.javamentor.java_pp_313.service;

import ru.javamentor.java_pp_313.model.Role;
import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();
    Set<Role> findRolesByName(String roleName);
}
