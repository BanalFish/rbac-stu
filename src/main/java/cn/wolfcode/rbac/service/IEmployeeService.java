package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.qo.EmployeeQueryObject;
import cn.wolfcode.rbac.qo.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IEmployeeService {

	List<Employee> selectList(EmployeeQueryObject qo);

	PageResult<Employee> selectPage(EmployeeQueryObject qo);

	void update(Employee employee,Long[] ids);

	void insert(Employee employee,Long[] ids);

	Employee selectById(Long id);

	List<Role> selectRoleByEmployeeId(Long employeeId);

	void deleteEmp(Long employeeId);

	Employee selectByNameAndPass(String username, String password);
}
