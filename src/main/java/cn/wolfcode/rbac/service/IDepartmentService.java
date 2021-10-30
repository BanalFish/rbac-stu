package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.qo.DepartmentQueryObject;
import cn.wolfcode.rbac.qo.PageResult;

import java.util.List;

public interface IDepartmentService {

	/**
	 * 查询全部
	 */
	List<Department> selectList();

	PageResult<Department> selectPage(DepartmentQueryObject queryObject);


	/**
	 * 根据id删除部门信息
	 */
	void delete(Long id);

	/**
	 * 保存部门信息
	 */
	void save(Department department);

}
