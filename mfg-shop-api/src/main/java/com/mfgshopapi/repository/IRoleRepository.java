package com.mfgshopapi.repository;

import com.mfgshopapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByNameRole(String nameRole);
}
