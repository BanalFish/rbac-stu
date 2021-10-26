package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.mapper.RoleMapper;
import cn.wolfcode.rbac.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {


    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectRoleAll() {
        return roleMapper.selectRoleAll();
    }

    @Override
    public Role selectById(Long roleId) {
       return roleMapper.selectById(roleId);
    }

    @Override
    public void insert(Role role, Long[] permiIds) {
        roleMapper.insert(role);
        for(Long id:permiIds){
            roleMapper.insertRelationPermissionAndRole(id,role.getId());
        }
    }


    @Override
    public List<Permission> selectPermissionByRoleId(Long roleId) {
        return roleMapper.selectPermissionByRoleId(roleId);
    }

    @Override
    public void deletePermiById(Long roleId) {
        roleMapper.deletePermiById(roleId);
    }

    @Override
    public void deleteRoleById(Long roleId) {
        roleMapper.deleteRoleById(roleId);
    }

    @Override
    public void update(Role role, Long[] permiIds) {
        if(permiIds.length>0 && (permiIds!=null)){
            roleMapper.update(role);
            roleMapper.deletePermiById(role.getId());
            for(Long id:permiIds){
                roleMapper.insertRelationPermissionAndRole(id,role.getId());
            }
        }
    }


}
