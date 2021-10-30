package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPermissionService {
    List<Permission> selectAll();

    void insert(Permission permission);

    void insertBatch(@Param("permissions") List<Permission> permissions);

    void deleteById(Long id);
}
