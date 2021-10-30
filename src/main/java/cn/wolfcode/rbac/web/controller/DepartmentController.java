package cn.wolfcode.rbac.web.controller;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.qo.DepartmentQueryObject;
import cn.wolfcode.rbac.qo.PageResult;
import cn.wolfcode.rbac.service.IDepartmentService;
import cn.wolfcode.rbac.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/department")
@Controller
public class DepartmentController {

	@Autowired
	private IDepartmentService departmentService;

	//localhost:8080/department/listPage
	@RequestMapping("/listPage")
	public ModelAndView listPage(DepartmentQueryObject queryObject){
		ModelAndView mv = new ModelAndView();
		PageResult<Department> pageResult = departmentService.selectPage(queryObject);
		//设置数据到作用域 Model
		mv.addObject("pageResult",pageResult);
		//找视图 View (WEB-INF/views/department/list.jsp)
		// prefix:    WEB-INF/views/ + /department/list + .jsp
		mv.setViewName("/department/list");
		return mv;
	}

//	@RequiredPermission(name = "部门列表",expression = "department:list")
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		List<Department> departments = departmentService.selectList();
		//设置数据到作用域 Model
		mv.addObject("departments", departments);
		//找视图 View (WEB-INF/views/department/list.jsp)
		// prefix:    WEB-INF/views/ + /department/list + .jsp
		mv.setViewName("/department/list");
		return mv;
	}

	/**
	 * 需求:删除操作
	 * 流程:
	 * 1、点击删除按钮
	 * 2、发起请求。http://localhost:8082/department/delete?id=15
	 *  id = ? (15)
	 * 3、接收请求。获取参数 id (Controller)
	 * 4、执行删除操作
	 *
	 * 持久层(Mapper接口、Mapper.xml)
	 * 业务层(接口 实现类)
	 * 控制器(XXXController)
	 *
	 * 5、回到列表
	 */
	@RequiredPermission(name="部门删除",expression = "department:delete")
	@RequestMapping("/delete")
	public String delete(Long id) {
		//接收参数
		//执行删除操作
		departmentService.delete(id);
		//回到列表
		//重定向
		return "redirect:/department/listPage";
	}

	@RequiredPermission(name = "部门添加/编辑页面",expression = "department:input")
	@RequestMapping("/input")
	public ModelAndView input(Long id) {
		ModelAndView mv = new ModelAndView();
		//WEB-INF/views/department/input.jsp  View
		mv.setViewName("/department/input");
		return mv;
	}

	@RequiredPermission(name = "部门添加/保存页面",expression = "department:saveOrUpdate")
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Department department) {
		//执行保存操作
		departmentService.save(department);
		return "redirect:/department/listPage";
	}


}
