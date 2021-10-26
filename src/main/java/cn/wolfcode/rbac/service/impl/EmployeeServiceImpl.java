package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.mapper.EmployeeMapper;
import cn.wolfcode.rbac.qo.EmployeeQueryObject;
import cn.wolfcode.rbac.qo.PageResult;
import cn.wolfcode.rbac.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public List<Employee> selectList(EmployeeQueryObject qo) {
		return employeeMapper.selectList(qo);
	}

	@Override
	public PageResult<Employee> selectPage(EmployeeQueryObject qo) {
		PageResult<Employee> pageResult = null;
		int totalCount = employeeMapper.selectCount(qo);
		if (totalCount == 0){
			pageResult = new PageResult<>(qo.getCurrentPage(),qo.getPageSize(),
					0,new ArrayList<>());
		}else{
			List<Employee> employeeList = employeeMapper.selectList(qo);
			pageResult = new PageResult<>(qo.getCurrentPage(),qo.getPageSize(),
					totalCount,employeeList);
		}
		return pageResult;
	}

	@Override
	public void update(Employee employee, Long[] ids) {
			employeeMapper.update(employee);
			employeeMapper.deleteRoleAndPermi(employee.getId());
		for(Long id:ids){
				employeeMapper.insertRelationEmployeeIdAndRoleId(employee.getId(),id);
			}

	}

	@Override
	public void insert(Employee employee, Long[] ids) {
			employeeMapper.insert(employee);
			for (Long id : ids){
				employeeMapper.insertRelationEmployeeIdAndRoleId(employee.getId(),id);
			}

	}

	@Override
	public Employee selectById(Long id) {
		return employeeMapper.selectById(id);
	}

	@Override
	public List<Role> selectRoleByEmployeeId(Long employeeId) {
		return employeeMapper.selectRoleByEmployeeId(employeeId);
	}

	@Override
	public void deleteEmp(Long employeeId) {
		employeeMapper.delete(employeeId);
	}

	@Override
	public Employee selectByNameAndPass(String username, String password) {
		return employeeMapper.selectByNameAndPass(username,password);
	}


}
