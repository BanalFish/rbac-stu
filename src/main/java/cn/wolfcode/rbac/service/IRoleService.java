package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> selectRoleAll();

    Role selectById(Long roleId);
    List<Permission> selectPermissionByRoleId(Long roleId);

    //增加
    void insert(Role role,Long[] permiIds);
    //删除
    void deletePermiById(Long roleId);
    void deleteRoleById(Long roleId);

    //编辑
    void update(Role role,Long[] permiIds);
}
