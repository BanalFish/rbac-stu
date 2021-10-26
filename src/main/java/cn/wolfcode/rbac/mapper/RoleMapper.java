package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    //显示全部
    List<Role> selectRoleAll();

    Role selectById(Long roleId);
    List<Permission> selectPermissionByRoleId(Long roleId);

    //增加
    void insert(Role role);
    void insertRelationPermissionAndRole(@Param("permiId")Long permiId,@Param("roleId")Long roleId);


    //删除
    void deletePermiById(Long id);
    void deleteRoleById(Long id);

    //编辑
    void update(Role role);

}
