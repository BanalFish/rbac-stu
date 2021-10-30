package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.qo.DepartmentQueryObject;

import java.util.List;

public interface DepartmentMapper {

	/**
	 * 查询部门信息
	 */
	List<Department> selectList();

	/**
	 * 查询部门分页
	 */
	List<Department> selectListPage(DepartmentQueryObject queryObject);

	/**
	 *
	 */

	/**
	 * 查询数据总条数
	 */
	int selectCount();

	/**
	 * 根据id删除部门信息
	 */
	void delete(Long id);

	/**
	 * 保存部门信息
	 */
	void insert(Department department);


}
