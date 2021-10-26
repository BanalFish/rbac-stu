package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> selectAll();

    void deleteById(Long id);
}
