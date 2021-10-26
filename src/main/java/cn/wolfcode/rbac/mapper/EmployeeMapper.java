package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.qo.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {

	/**
	 * 查询员工信息
	 */
	List<Employee> selectList(EmployeeQueryObject qo);

	/**
	 * 查询数量
	 */
	int selectCount(EmployeeQueryObject qo);

	/**
	 * 添加员工和角色关系
	 * @param employee
	 */
	void insert(Employee employee);
	void insertRelationEmployeeIdAndRoleId(@Param("employeeId")Long employeeId, @Param("roleId")Long roleId);

	/**
	 * 修改时显示
	 * @param employeeId
	 * @return
	 */
	Employee selectById(Long employeeId);
	List<Role> selectRoleByEmployeeId(Long employeeId);

	/**
	 * 更新
	 */
	void update(Employee employee);


	/**
	 * 删除员工
	 */
	void delete(Long employeeId);

	/**
	 * 删除员工与权限的关系
	 */
	void deleteRoleAndPermi(Long employeeId);

	Employee selectByNameAndPass(@Param("username") String username, @Param("password") String password);

}
