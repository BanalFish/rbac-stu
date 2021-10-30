package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    List<Permission> selectAll();

    void insert(Permission permission);

    void insertBatch(@Param("permissions") List<Permission> permissions);

    void deleteById(Long id);
}
