package com.murtazin.bootstrap.service;

import com.murtazin.bootstrap.model.Role;
import com.murtazin.bootstrap.repository.RoleRepository;
import com.murtazin.bootstrap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private final UserRepository userRepo;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(UserRepository userRepo, RoleRepository roleRepository) {
        this.userRepo = userRepo;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(String role) {
        return roleRepository.findByName(role);
    }
}
