package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    List<Permission> selectAll();

    //查询权限表达式
    List<String> selectExpressions();

    List<String> selectExpressionsByEmpId(Long EmpId);

    void insert(Permission permission);

    void insertBatch(@Param("permissions") List<Permission> permissions);

    void deleteById(Long id);
}
