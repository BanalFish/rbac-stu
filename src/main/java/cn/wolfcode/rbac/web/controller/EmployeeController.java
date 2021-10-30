package cn.wolfcode.rbac.web.controller;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.qo.EmployeeQueryObject;
import cn.wolfcode.rbac.qo.PageResult;
import cn.wolfcode.rbac.service.IDepartmentService;
import cn.wolfcode.rbac.service.IEmployeeService;
import cn.wolfcode.rbac.service.IRoleService;
import cn.wolfcode.rbac.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/employee")
@Controller
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IRoleService roleService;

//	@RequiredPermission(name = "员工列表",expression = "employee:list")
	@RequestMapping("/list")
	public ModelAndView list(EmployeeQueryObject qo) {
		ModelAndView mv = new ModelAndView();
		List<Employee> employees = employeeService.selectList(qo);
		mv.addObject("employees", employees);
		List<Department> departments = departmentService.selectList();
		mv.addObject("departments",departments);
		mv.addObject("qo", qo);
		mv.setViewName("/employee/list");
		return mv;
	}

	@RequiredPermission(name = "员工编辑",expression = "employee:input")
	@RequestMapping("/input")
	public ModelAndView input(Long id){
		ModelAndView mv=new ModelAndView();

		if(id!=null){
			Employee employee=employeeService.selectById(id);
			mv.addObject("employee",employee);
			List<Role> roles=employeeService.selectRoleByEmployeeId(id);
			mv.addObject("empRoleList",roles);

		}

		List<Department> departments=departmentService.selectList();
		mv.addObject("departments",departments);
		List<Role> roles=roleService.selectRoleAll();
		mv.addObject("roleList",roles);
		mv.setViewName("employee/input");
		return mv;
	}

	@RequiredPermission(name = "员工保存/更新",expression = "employee:saveOrUpdate")
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Employee employee,Long[] ids){
		if(ids.length>0&&ids!=null&&employee.getId()==null){
			employeeService.insert(employee,ids);
		}
		else{
			employeeService.update(employee,ids);
		}

		return "redirect:/employee/list";
	}

	/**
	 * 没有弹出警告
	 * @param id
	 * @return
	 */
	@RequiredPermission(name = "员工删除",expression = "employee:delete")
	@RequestMapping("/delete")
	public String delete(Long id){
		employeeService.deleteEmp(id);
		return "redirect:/employee/list";
	}

}
