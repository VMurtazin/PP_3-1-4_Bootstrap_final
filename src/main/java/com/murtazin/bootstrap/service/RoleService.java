package com.murtazin.bootstrap.service;

import com.murtazin.bootstrap.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> allRoles();

    Role getRole(String role);
}
