package cn.wolfcode.rbac.web.controller;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.service.impl.PermissionService;
import cn.wolfcode.rbac.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/list")
    public ModelAndView listAll(){
        List<Role> roles = roleService.selectRoleAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("roles",roles);
        mv.setViewName("/role/list");
        return mv;

    }

    @RequestMapping("/input")
    public ModelAndView input(Long id){
        ModelAndView mv=new ModelAndView();

        if(id!=null){
            Role role=roleService.selectById(id);
            mv.addObject("role",role);
            List<Permission> rolePermission = roleService.selectPermissionByRoleId(id);
            mv.addObject("rolePermission",rolePermission);
        }
        List<Permission> permissions = permissionService.selectAll();
        mv.addObject("permissionList",permissions);

        mv.setViewName("role/input");
        return mv;
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role role,Long[] ids){
        //添加
        if(ids.length>0&&ids!=null&&role.getId()==null){
            roleService.insert(role,ids);
        }
        //编辑
        else{
            roleService.update(role,ids);
        }

        return "redirect:/role/list";
    }

    /**
     * 没有弹出警告
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(Long id){
        roleService.deleteRoleById(id);
        return "redirect:/role/list";
    }
}
