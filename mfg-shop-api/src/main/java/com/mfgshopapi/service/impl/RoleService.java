package com.mfgshopapi.service.impl;

import com.mfgshopapi.model.Role;
import com.mfgshopapi.repository.IRoleRepository;
import com.mfgshopapi.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public Role findRoleByNameRole(String nameRole) {
        return roleRepository.findRoleByNameRole(nameRole);
    }
}
