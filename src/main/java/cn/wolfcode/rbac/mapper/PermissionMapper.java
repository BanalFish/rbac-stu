package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Permission;

import java.util.List;

public interface PermissionMapper {
    List<Permission> selectAll();
    void deleteById(Long id);
}
