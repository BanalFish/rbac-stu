package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.mapper.DepartmentMapper;
import cn.wolfcode.rbac.qo.DepartmentQueryObject;
import cn.wolfcode.rbac.qo.PageResult;
import cn.wolfcode.rbac.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public List<Department> selectList() {
		return departmentMapper.selectList();
	}

	@Override
	public PageResult<Department> selectPage(DepartmentQueryObject queryObject) {
		PageResult<Department> pageResult = null;
		int totalCount = departmentMapper.selectCount();
		if (totalCount == 0){
			//表里面 没有我们查询的数据
			pageResult = new PageResult<>(queryObject.getCurrentPage(),queryObject.getPageSize(),
					0,new ArrayList<>());
		}else{
			List<Department> departmentList = departmentMapper.selectListPage(queryObject);
			pageResult = new PageResult<>(queryObject.getCurrentPage(),queryObject.getPageSize(),
					totalCount,departmentList);
		}
		return pageResult;
	}

	@Override
	public void delete(Long id) {
		departmentMapper.delete(id);
	}

	@Override
	public void save(Department department) {
		departmentMapper.insert(department);
	}


}
