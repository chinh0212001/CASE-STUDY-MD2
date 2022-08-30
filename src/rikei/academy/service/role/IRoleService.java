package rikei.academy.service.role;

import rikei.academy.model.Role;
import rikei.academy.model.RoleName;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();
    void save(Role role);
    Role findByRoleName(RoleName roleName);
}
